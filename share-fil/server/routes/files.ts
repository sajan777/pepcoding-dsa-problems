import express from 'express';
import multer from 'multer';
import { UploadApiResponse, v2 as cloudinary } from "cloudinary";
import File from '../models/File';
import https from 'https';
import nodemailer from "nodemailer";
import createEmailTemplate from '../utils';

const router = express.Router();

const storage = multer.diskStorage({});
const upload = multer({
    storage
})

router.post("/upload",upload.single("myFile"),async (req,res) => {
    try {
        if(!req.file) return res.status(400).json({message: 'Invalid file'});
        let uploadedFile:UploadApiResponse;
        try {
            uploadedFile = await cloudinary.uploader.upload(req.file.path,{
                folder: 'shareme',
                resource_type: 'auto'
            })
        } catch (error) {
            console.log(error);
            return res.status(500).json({message: 'Cloudinary Upload Error'});
        }
        const {  originalname } = req.file;
        const {  secure_url,bytes,format } = uploadedFile;
        const file = await File.create({
            filename: originalname,
            secure_url,
            sizeInBytes: bytes,
            format
        })
        res.status(200).json({
            id: file._id,
            downloadPageLink: `${process.env.API_BASE_ENDPOINT_CLIENT}download/${file._id}`
        });
    } catch (error) {
        res.status(500).json({message: `Server error ${error}`})
    }
})

router.get('/:id',async (req,res) => {
    try {
        const fileId = req.params.id;
        const file = await File.findById(fileId);
        if(!file) {
            return res.status(404).json({message: 'Invalid file'});
        }
        const {filename,format,sizeInBytes,id} = file;
        return res.status(200).json({
            name: filename,
            size: sizeInBytes,
            format: format,
            id: id
        })
    } catch (error) {
        res.status(500).json({message: 'Error file fetching file.'})
    }
})

router.get('/:id/download',async (req,res) => {
    try {
        const fileId = req.params.id;
        const file = await File.findById(fileId);
        if(!file) {
            return res.status(404).json({message: 'Invalid file'});
        }
        https.get(file.secure_url,(fileStream) => fileStream.pipe(res));
    } catch (error) {
        res.status(500).json({message: 'Error file fetching file.'})
    }
})

router.post('/email',async (req,res) => {
    const { id,emailFrom,emailTo } = req.body;
    const file = await File.findById(id);
    if(!file) {
        return res.status(404).json({message: 'Invalid file'});
    }
    const transporter  = nodemailer.createTransport({
        // @ts-ignore
        host: process.env.SENDINBLUE_SMTP_HOST,
        port: process.env.SENDINBLUE_SMTP_PORT,
        secure: false,
        auth: {
            user: process.env.SENDINBLUE_SMTP_USER,
            pass: process.env.SENDINBLUE_SMTP_PASSWORD
        }
    });
    const {filename,sizeInBytes} = file;
    const fileSize = `${(Number(sizeInBytes)/(1024*1024)).toFixed(2)} MB`;
    const downloadPageLink = `${process.env.API_BASE_ENDPOINT_CLIENT}download/${id}`;
    const mailOptions = {
        from: emailFrom,
        to: emailTo,
        subject: 'File Shared with you!',
        text:  `${emailFrom} shared a file with you!`,
        html: createEmailTemplate(emailFrom,emailTo,filename,downloadPageLink,fileSize)
    };

    transporter.sendMail(mailOptions, async(error,_info) => {
        if(error) return res.status(500).json({message:'Server Error'});

        file.sender = emailFrom;
        file.receiver = emailTo;

        await file.save();
        return res.status(200).json({message: 'Email Sent Successfully'});
    })

})

export default router;
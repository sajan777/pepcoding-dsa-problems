import express from "express";
import dotenv from "dotenv";
import cors from "cors";
import connectDB from "./config/db";
import fileRoutes from './routes/files';
import { v2 as cloudinary } from "cloudinary";

const app = express();
dotenv.config();
cloudinary.config({
    cloud_name: process.env.CLOUDINARY_CLOUD,
    api_key: process.env.CLOUDINARY_KEY,
    api_secret: process.env.CLOUDINARY_SECRET,
})

connectDB();

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({
    extended: true
}))
app.get('/',(req,res)=>{
    res.send('Hello from express');
})
app.use('/api/files',fileRoutes)

const PORT = process.env.PORT;

app.listen(PORT, () => console.log(`Server is listening on PORT ${PORT}`));


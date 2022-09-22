import mongoose, { Document } from "mongoose";

const Schema = mongoose.Schema;

interface IFile extends Document {
    filename: string,
    secure_url: string,
    format: string,
    sizeInBytes: string,
    receiver?: string,
    sender?: string
}

const fileSchema = new Schema({
    filename: {
        type: String,
        require: true,
    },
    secure_url: {
        type:String,
        require:true
    },
    format: {
        type:String,
        require:true
    },
    sizeInBytes: {
        type:String,
        require:true
    },
    receiver: {
        type:String,
    },
    sender: {
        type:String,
    }
},{
    timestamps: true
})

export default mongoose.model<IFile>("File", fileSchema);
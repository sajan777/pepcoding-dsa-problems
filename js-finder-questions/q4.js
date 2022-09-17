/// Flatten and Object
const flattenObject = (obj,parent) => {
    const result = {};
    const generateObject = (obj,parent) => {
        for(let key in obj){
            const value = obj[key];
            const newParent = parent+key;
            if(typeof value === "object") {
                generateObject(value,newParent+'.');
            } else {
                result[newParent] = value;
            }
        }
    }
    generateObject(obj,parent);
    return result;
}

const obj = {A:'12',B:'23',C:{P:23,O:{L:56},Q:[1,2]}}
console.log(flattenObject(obj,""));
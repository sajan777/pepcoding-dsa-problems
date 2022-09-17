// Flatten an Array

const flattenArray = (arr) => {
    return arr.reduce((prevVal,currentValue) => {
        if(Array.isArray(currentValue)) {
            prevVal = prevVal.concat(flattenArray(currentValue));
        } else {
            prevVal.push(currentValue);
        }
        return prevVal;
    } ,[])
}

const arr = [[[[1,[1.1]]],2,3],[4,5]]
console.log(flattenArray(arr));
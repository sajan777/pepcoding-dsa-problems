// pollyfill for string.split() Uber

const split = (originalString,delimeter) => {
    if(delimeter === '') return Array.from(originalString);
    const res = [];
    const startSpliting = (str,idx) => {
        if(idx > originalString.length) return;
        const index = str.indexOf(delimeter);
        if(index >= 0){
            //found the delimeter in str
            res.push(str.substring(0,index));
            startSpliting(str.substring(index+delimeter.length),index+delimeter.length);
        }else{
            // not found delimeter push whole string
            res.push(str); 
        }
    }
    startSpliting(originalString,0);
    return res;
}

console.log(split("The quick the fox jumps the lazy dog","the"))
console.log(split("The quick the fox jumps the lazy dog",""))
console.log(split("The quick the fox jumps the lazy dog"," "))
console.log(split("The quick the fox jumps the lazy dog","."))
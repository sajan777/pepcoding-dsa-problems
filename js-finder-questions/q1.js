// Memoize function in javascript (Zomato)
function memoize(fn) {
    const cache = {};
    return (...args) => {
        const argsToString = JSON.stringify(args);
        if(argsToString in cache){
            console.log("args in cache");
            return cache[argsToString];
        }else{
            console.log("computing result");
            const res = fn.apply(this,args);
            cache[argsToString] = res;
            return res;
        }
    }
}

const addThreeNums = (a,b,c) => a+b+c;
const add = memoize(addThreeNums);
console.log(add(1,2,3));
console.log(add(1,2,3));


const factorial = memoize((x) => {
    if(x === 0) return 1;
    return x * factorial(x-1);
});


console.log(factorial(6));
console.log(factorial(5));
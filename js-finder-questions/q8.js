// Currying function return previous valuel



const curryFunc = () => {
    let prevValue = 0;
    return (newValue = 0) => {
        prevValue += newValue;
        return prevValue;
    }
}

const sum = curryFunc();
console.log(sum(1)); // 1
console.log(sum(2)); // 2 + 1 = 3
console.log(sum(3)); // 2 + 1 + 3 = 6
console.log(sum(4)); // 2 + 1 + 3 + 4 = 10
console.log(sum(5)); // 2 + 1 + 3 + 4 + 5 = 15
console.log(sum()); // 15
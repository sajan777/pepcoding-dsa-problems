const incr = document.getElementById('incr');
const decr = document.getElementById('decr');
const counterVal = document.querySelector('.counter-val');
const reset = document.getElementById('reset');
const countVal = document.getElementById('count-val');

let initVal = 0;

const renderHTML = () => {
    counterVal.innerHTML = initVal;
}

const increment = () => {
    initVal += (countVal.value * 1);
    renderHTML();
}
const decrement = () => {
    if(initVal <= 0){
        return;
    }
    initVal -= (countVal.value * 1);
    renderHTML();
}
const resetEverything = () => {
    initVal = 0;
    renderHTML();
}

incr.addEventListener('click',increment);
decr.addEventListener('click',decrement);
reset.addEventListener('click',resetEverything);
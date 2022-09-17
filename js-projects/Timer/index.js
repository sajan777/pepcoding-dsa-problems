const display = document.querySelector('.displays');
const buttons = document.querySelector('.buttons');

class Timer {
    msbRegex = /^[0-5]$/;
    lsbRegex = /^[0-9]$/;

    minutes = 0;
    seconds = 0;
    constructor(input1,input2,input3,input4,start,stop,reset,display){
        this.input1 = document.getElementById(input1);
        this.input2 = document.getElementById(input2);
        this.input3 = document.getElementById(input3);
        this.input4 = document.getElementById(input4);
        this.start = document.getElementById(start);
        this.stop = document.getElementById(stop);
        this.reset = document.getElementById(reset);
        this.display = display;
    }

    onReset(e){
        this.setControls(false,true);
        this.resetControls();
        this.resetTimerValue();
    }
    resetTimerValue(){
        this.input1.value = 0;
        this.input2.value = 1;
        this.input3.value = 0;
        this.input4.value = 5;
    }
    resetControls(){
        clearInterval(this.intervalId);
        this.display.classList.remove('contdown-running');
        this.setInputsDisabled(false);
    }
    onStart(){
        this.setControls(true,false);
        this.display.classList.add('contdown-running');
        this.setInputsDisabled(true);
        this.setTime();
        this.startTimer();
    }
    onStop(e){
        clearInterval(this.intervalId);
        this.setControls(false,true);
        this.display.classList.remove('contdown-running');
        this.setInputsDisabled(false);
    }
    setControls(startStatus = false,stopStatus = true){
        this.start.disabled = startStatus;
        this.stop.disabled = stopStatus;
    }

    onClick = (e) => {
        const target = e.target.id;
        if(target === 'start') {
            this.onStart();
        } else if(target === 'stop') {
            this.onStop();
        } else if(target === 'reset') {
            this.onReset();
        }
    }
    setInputsDisabled(disabled = false){
        this.input1.disabled = disabled;
        this.input2.disabled = disabled;
        this.input3.disabled = disabled;
        this.input4.disabled = disabled;
    }
    setTime(){
        this.minutes = (this.input1.value + this.input2.value) * 1;
        this.seconds = (this.input3.value + this.input4.value) * 1;
        console.log(this.minutes + ":" + this.seconds);
    }
    startTimer(){
        if(this.minutes === 0 && this.seconds === 0) {
            this.onReset();
            return;
        }
        this.intervalId = setInterval(() => {
            this.seconds -= 1;
            if(this.seconds <= 0) {
                this.minutes -= 1;
                this.seconds = 59;
            }
            if(this.minutes === 0 && this.seconds === 0){
                this.onReset();
            }
            this.setDisplay(this.minutes,this.seconds);
        },1000);
    }
    onInput = event => {
        const value = Number(event.data);
        console.log(value);
        if(typeof value === 'number') {
            if(event.target.id === 'input1' || event.target.id === 'input3') {
                this.onValueEntry(this.msbRegex,event.target,value);
            } else if(event.target.id === 'input2' || event.target.id === 'input4') {
                this.onValueEntry(this.lsbRegex,event.target,value);
            }
        }
    }
    onValueEntry(regex,target,value){
        if(regex.test(value)){
            target.value = value;
            target.nextElementSibling?.focus();
            target.nextElementSibling?.select()
        }else{
            target.value = 0;
            target.select();
        }
    }
    setDisplay(minutes,seconds){
        const min = String(minutes);
        const sec = String(seconds);
        console.log(minutes,seconds);
        [this.input1.value,this.input2.value] = min.length === 2 ? [min[0],min[1]] : [0,min[0]];
        [this.input3.value,this.input4.value] = sec.length === 2 ? [sec[0],sec[1]] : [0,sec[0]];
    }
}

const timer = new Timer('input1','input2','input3','input4','start','stop','reset',display);
display.addEventListener('input',timer.onInput);
buttons.addEventListener('click',timer.onClick);



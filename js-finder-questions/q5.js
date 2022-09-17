// Promise.all pollyfill (Share Chat)

<!----------------------------------WRONG IMPLENTATION DONT CHECK THIS-------------------------------------->

const dummyAPI = (time) => {
    return new Promise((resolve,reject) => {
        setTimeout(() => {
            resolve(time)
        }, time);
    })
}

const tasksArray = [dummyAPI(1000),dummyAPI(2000),dummyAPI(500)]

const promiseAllPollyfill = (tasks) => {
    const result = [];
    return new Promise((resolve,reject) => {
        tasks.forEach((task,idx) => {
            task.then(data => {
                result[idx] = data;
                if(idx === tasks.length-1) resolve(result);
            }).catch(err => reject(err)); 
        });
    })
}

promiseAllPollyfill(tasksArray).then(d => console.log(`data is ${d}`)).catch(err => console.log(err));
const levels = document.querySelectorAll('.leveloperatorswrap');
const lift1 = document.getElementById('lift-1');

const handleLevelClick = (e) => {
    const {id} = e.target.dataset;
    if(!id) return;
    const val = id.split('-')[1]*100;
    lift1.style.transform = `translateY(-${val}px)`;
}

levels.forEach(level => {
    level.addEventListener('click',handleLevelClick);
})
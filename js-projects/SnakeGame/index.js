const scoreCard = document.getElementById('score-card');
class Board {
    score = 0;

    constructor(scoreCard){
        this.scoreCard = scoreCard;
    }

    updateScore(){
        this.score += 10;
        this.displayScore();
    }

    displayScore(){
        this.scoreCard.innerHTML = this.score;
    }
}
const board = new Board(scoreCard);

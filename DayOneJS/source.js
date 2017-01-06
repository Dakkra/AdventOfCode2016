const input = "L1, L5, R1, R3, L4, L5, R5, R1, L2, L2, L3, R4, L2, R3, R1, L2, R5, R3, L4, R4, L3, R3, R3, L2, R1, L3, R2, L1, R4, L2, R4, L4, R5, L3, R1, R1, L1, L3, L2, R1, R3, R2, L1, R4, L4, R2, L189, L4, R5, R3, L1, R47, R4, R1, R3, L3, L3, L2, R70, L1, R4, R185, R5, L4, L5, R4, L1, L4, R5, L3, R2, R3, L5, L3, R5, L1, R5, L4, R1, R2, L2, L5, L2, R4, L3, R5, R1, L5, L4, L3, R4, L3, L4, L1, L5, L5, R5, L5, L2, L1, L2, L4, L1, L2, R3, R1, R1, L2, L5, R2, L3, L5, L4, L2, L1, L2, R3, L1, L4, R3, R3, L2, R5, L1, L3, L3, L3, L5, R5, R1, R2, L3, L2, R4, R1, R1, R3, R4, R3, L3, R3, L5, R2, L2, R4, R5, L4, L3, L1, L5, L1, R1, R2, L1, R3, R4, R5, R2, R3, L2, L1, L5";
const Direction = {
    NORTH: "North",
    EAST: "East",
    SOUTH: "South",
    WEST: "West"
};
var answerElem = document.getElementById("answer");
var currentOriantation = Direction.NORTH;
var xDelta = 0;
var yDelta = 0;

window.onload = function () {
    processInput();
    var distance = getDistance();
    answerElem.innerHTML = distance;
};

function processInput() {
    console.log(currentOriantation);
    var numBuffer = "";
    for (var i = 0; i < input.length; i++) {
        console.log(input[i]);
        switch (input[i]) {
            case "R": {
                turnRight();
                console.log("RIGHT");
                console.log(currentOriantation);
                break;
            }
            case "L" : {
                turnLeft();
                console.log("LEFT");
                console.log(currentOriantation);
                break;
            }
            case " ": {
                break;
            }
            case ",": {
                move(parseInt(numBuffer));
                numBuffer = "";
                break;
            }
            default: {
                numBuffer += input[i];
                break;
            }
        }
    }
}

function getDistance() {
    return Math.abs(xDelta) + Math.abs(yDelta);
}

function move(magnitude) {
    console.log("MOVE: " + currentOriantation + " by " + magnitude);
    switch (currentOriantation) {
        case Direction.NORTH: {
            yDelta += magnitude;
            break;
        }
        case Direction.SOUTH: {
            yDelta -= magnitude;
            break;
        }
        case Direction.EAST: {
            xDelta += magnitude;
            break;
        }
        case Direction.WEST: {
            xDelta -= magnitude;
            break;
        }
        default:
            break;
    }
    console.log("XDELTA: " + xDelta);
    console.log("YDELTA: " + yDelta);
}

function turnLeft() {
    switch (currentOriantation) {
        case Direction.NORTH: {
            currentOriantation = Direction.WEST;
            break;
        }
        case Direction.WEST: {
            currentOriantation = Direction.SOUTH;
            break;
        }
        case Direction.SOUTH: {
            currentOriantation = Direction.EAST;
            break;
        }
        case Direction.EAST: {
            currentOriantation = Direction.NORTH;
            break;
        }
        default:
            break;
    }
}

function turnRight() {
    switch (currentOriantation) {
        case Direction.NORTH: {
            currentOriantation = Direction.EAST;
            break;
        }
        case Direction.WEST: {
            currentOriantation = Direction.NORTH;
            break;
        }
        case Direction.SOUTH: {
            currentOriantation = Direction.WEST;
            break;
        }
        case Direction.EAST: {
            currentOriantation = Direction.SOUTH;
            break;
        }
        default:
            break;
    }
}
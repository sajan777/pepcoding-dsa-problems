// DOM Finder(google)

const getPathFromChildToParent = (child,parent) => {
    let currentNode = child;
    const path = [];
    while(currentNode != parent){
        const parentElement = currentNode.parentElement;
        const childrenArray = Array.from(parentElement.children);
        path.push(childrenArray.indexOf(currentNode));
        currentNode = parentElement;
    }
    return path;
}

const getValueFromPath = (parent,path) => {
    let currentNode = parent;
    while (path.length) {
        currentNode = currentNode.children[path.pop()];
    }
    return currentNode.innerText;
}

const findNodeValue = () => {
    const nodeA = document.querySelector('#nodeA');
    const rootA = document.querySelector('#rootA');
    const rootB = document.querySelector('#rootB');
    const path = getPathFromChildToParent(nodeA,rootA);
    return getValueFromPath(rootB,path);
}


console.log(findNodeValue());
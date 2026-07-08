console.log("Hello!");

const first = 3;
const second = 7;
console.log(first)

const str = "Hemalatha";
console.log(str);

const fruits = ["Strawberry", "Dragon Fruit", "Pinapple"];
console.log("I like The",fruits[1]);
fruits[2] = "Grapes"
console.log(fruits[2]);

const arr= [2, "name", false, 2.4]
console.log(arr)
for(let i = 0; i < arr.length; i++){
    console.log(arr[i])
}

const NextStep = document.getElementById("ptag")
NextStep.innerHTML = "This is Fourth Class of My Full Stack Development Placement Training"

function newFunction(){
    console.log("I am Practicing My coding Knowledge")
}

const input= document.getElementById("eventListener")
const output= document.getElementById("Ptag") // For this alone we must use capital P for handling the eventListener
input.addEventListener("input", ()=>{
    output.textContent = input.value
})

const newvar = document.createElement("p");
newvar.textContent = "New Paragraph";
document.body.appendChild(newvar);
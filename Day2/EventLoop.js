console.log("A")
setTimeout(()=>{
    console.log("B")
    console.log("D")
}, 0)
console.log("C")


const promise = new Promise((resolve, reject)=>{
    setTimeout(example, 4500)
    setTimeout(example1, 0)

    // const success = true;
    // if(success){
    //     console.log("Done")
    // }
    // else{
    //     console.log("Fail")
    // }
})
promise.then(res=>{console.log(res)})
.catch(err=> console.log(err))

function example(){
    console.log("Hi Everyone")
}
const example1 = ()=>{
    console.log("Hello World")
}
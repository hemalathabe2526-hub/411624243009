let observers=[]

function attach(observer){
    observers.push(observer)
}
function detach(observer){
    let newobserver=[]
    for(let i of observers){
        if(i!==observer){
            newobserver.push(i)
        }
    }
    observer=newobserver
}

function notify(){
    for(let i of observers){
        console.log(i+" : notified")
    }
}

attach("A")
attach("B")
notify()
attach("C")
detach("B")
notify()
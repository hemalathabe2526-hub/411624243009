let students=[]

function addStudent(student){
    students.push(student)
}

function removeStudent(student){
    let newStudents=[]
    for(let i of students){
        if(i!==student){
            newStudents.push(i)
        }
    }
    students=newStudents
}

function listStudents(){
    for(let i of students){
        console.log(i+" : enrolled")
    }
}

function notifyStudents(){
    for(let i of students){
        console.log(i+" : notified")
    }
}

addStudent("John")
addStudent("Jane")
listStudents()
addStudent("Mike")
removeStudent("Jane")
listStudents()
notifyStudents()
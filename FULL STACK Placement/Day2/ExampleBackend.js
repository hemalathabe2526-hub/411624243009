// fetch("https://jsonplaceholder.typicode.com/posts",
//     {
//         method : "POST",
//         headers : {
//             'Content-Type' : 'application/json'
//         },
//         body: JSON.stringify({
//             title : 'My Project',
//             body : 'Learing the Backend Process',
//             userId : 4
//         })
//     }
// )
// .then(response => response.json())
// .then(data => {console.log(data)})


// fetch("https://jsonplaceholder.typicode.com/posts/1",
//    {
//    method:"DELETE"
//    }
// )
// .then(response => response.json())
// .then(data =>{console.log(data)})


fetch("https://jsonplaceholder.typicode.com/posts",
    {
        method : "PATCH",
        headers : {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
            title : 'My Project',
        })
    }
)
.then(response => response.json())
.then(data => {console.log(data)})
const api = "http://localhost:8080/api/students";

window.onload = loadStudents;

function loadStudents() {

fetch(api)
.then(res => res.json())
.then(data => {

let rows = "";

data.forEach(s => {

rows += `
<tr>
<td>${s.id}</td>
<td>${s.firstName} ${s.lastName}</td>
<td>${s.email}</td>
<td>${s.department}</td>
<td>${s.gpa}</td>
<td>
<button class="btn btn-danger btn-sm"
onclick="deleteStudent(${s.id})">
Delete
</button>
</td>
</tr>
`

})

document.getElementById("totalStudents").innerText = data.length;

})

}

function addStudent(){

let student = {

firstName: document.getElementById("firstName").value,
lastName: document.getElementById("lastName").value,
email: document.getElementById("email").value,
age: document.getElementById("age").value,
department: document.getElementById("department").value,
gpa: document.getElementById("gpa").value

};

fetch(api,{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify(student)
})
.then(()=>loadStudents());

}

function deleteStudent(id){

fetch(api + "/" + id,{
method:"DELETE"
})
.then(()=>loadStudents());

}
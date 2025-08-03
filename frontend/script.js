const API_URL = 'http://localhost:8080/api/students';

// Add Student
async function addStudent() {
    const name = document.getElementById('studentName').value;
    const email = document.getElementById('studentEmail').value;
    const age = document.getElementById('studentAge').value
    if (!name || !email || !age) {
        alert('Please fill all fields');
        return;
    }
    
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, email,age })
        });
        
        if (response.ok) {
            alert('Student added!');
            clearForm();
            getAllStudents();
        } else {
            alert('Error adding student');
        }
    } catch (error) {
        alert('Connection error');
    }
}

// Update Student
async function updateStudent() {
    const id = document.getElementById('studentId').value;
    const name = document.getElementById('studentName').value;
    const email = document.getElementById('studentEmail').value;
    const age = Number(document.getElementById('studentAge').value);
    
    if (!id || !name || !email|| !age) {
        alert('Please fill in all fields');
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({name, email,age })
        });
        
        if (response.ok) {
            alert('Student updated!');
            clearForm();
            getAllStudents();
        } else {
            alert('Error updating student');
        }
    } catch (error) {
        alert('Connection error');
    }
}

// Get All Students
async function getAllStudents() {
    try {
        const response = await fetch(API_URL);
        const students = await response.json();
        
        const tbody = document.querySelector('#studentsTable tbody');
        tbody.innerHTML = '';
        
        students.forEach(student => {
            tbody.innerHTML += `
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <td>${student.age}</td>
                    <td>
                        <button onclick="deleteStudent(${student.id})">Delete</button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        alert('Error loading students');
    }
}

// Check Student by ID
async function checkStudent() {
    const id = document.getElementById('searchIdInput').value;
    const result = document.getElementById('searchResult');
    
    if (!id) {
        result.textContent = 'Enter an ID';
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/exists/${id}`);
        if (response.ok) {
            const message = await response.text();
            result.textContent = message;
        } else {
            result.textContent = 'Student not found';
        }
    } catch (error) {
        result.textContent = 'Connection error';
    }
}

// Delete Student
async function deleteStudent(id) {
    if (!confirm('Delete this student?')) return;
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            alert('Student deleted!');
            getAllStudents();
        } else {
            alert('Error deleting student');
        }
    } catch (error) {
        alert('Connection error');
    }
}

// Clears form
function clearForm() {
    document.getElementById('studentId').value = '';
    document.getElementById('studentName').value = '';
    document.getElementById('studentEmail').value = '';
    document.getElementById('studentAge').value = '';
    document.getElementById('searchIdInput').value='';
}

//Load studs on page load
window.onload = getAllStudents;
// This function runs when the document is fully loaded
document.addEventListener('DOMContentLoaded', function () {
    // Fetch all employees when the page loads
    fetchAllEmployees();

    // Add an event listener to the fetch button
    document.getElementById('fetchEmployeeBtn').addEventListener('click', fetchEmployeeById);
});

// Function to fetch all employees from the server
function fetchAllEmployees() {
    fetch('/api/employees') // Send a request to the server to get all employees
        .then(response => response.json()) // Convert the response to JSON
        .then(data => {
            // Get the table body element
            const tableBody = document.querySelector('#employeeTable tbody');

            // Clear the table body
            tableBody.innerHTML = '';

            // Loop through the data and add a row for each employee
            data.forEach(employee => {
                const row = document.createElement('tr'); // Create a new table row

                // Create and append the ID cell
                const idCell = document.createElement('td'); // Create a new table cell for the ID
                idCell.textContent = employee.id; // Set the cell's text to the employee's ID
                row.appendChild(idCell); // Add the cell to the row

                // Create and append the Name cell
                const nameCell = document.createElement('td'); // Create a new table cell for the name
                nameCell.textContent = employee.name; // Set the cell's text to the employee's name
                row.appendChild(nameCell); // Add the cell to the row

                // Create and append the Position cell
                const positionCell = document.createElement('td'); // Create a new table cell for the position
                positionCell.textContent = employee.position; // Set the cell's text to the employee's position
                row.appendChild(positionCell); // Add the cell to the row

                // Create and append the Salary cell
                const salaryCell = document.createElement('td'); // Create a new table cell for the salary
                salaryCell.textContent = employee.salary; // Set the cell's text to the employee's salary
                row.appendChild(salaryCell); // Add the cell to the row

                // Append the row to the table body
                tableBody.appendChild(row); // Add the row to the table body
            });
        })
        .catch(error => showErrorDiv(error)); // Show an error message if something goes wrong
}

// Function to fetch a specific employee by ID from the server
function fetchEmployeeById() {
    const employeeId = document.getElementById('employeeId').value; // Get the employee ID from the input field

    fetch(`/api/employees/${employeeId}`) // Send a request to the server to get the employee by ID
        .then(response => {
            if (!response.ok) { // If the response is not OK, handle the error
                return response.json().then(errorData => {
                    throw new Error(`${response.status}: ${errorData.message}`); // Throw an error with status and message
                });
            }
            return response.json(); // Convert the response to JSON
        })
        .then(employee => {
            // Display the employee details
            document.getElementById('empId').textContent = employee.id; // Set the text content for the ID
            document.getElementById('empName').textContent = employee.name; // Set the text content for the name
            document.getElementById('empPosition').textContent = employee.position; // Set the text content for the position
            document.getElementById('empSalary').textContent = employee.salary; // Set the text content for the salary
        })
        .catch(error => {
            showErrorDiv(error); // Show an error message if something goes wrong
        });
}

// Function to show an error message
function showErrorDiv(error) {
    const errorDiv = document.getElementById('errorDiv'); // Get the error message div
    const [statusCode, errorMessage] = error.message.split(': '); // Split the error message into status code and message

    // Set the error code and message in the div
    document.getElementById('errorCode').textContent = statusCode; // Set the text content for the error code
    document.getElementById('errorMessage').textContent = errorMessage; // Set the text content for the error message

    // Display the error div
    errorDiv.style.display = 'block'; // Make the error message div visible
}

const loginBtn = document.getElementById('loginBtn')
loginBtn.addEventListener('click', handleLogin)

function handleLogin() {
    const email = document.getElementById('formGroupExampleInput').value;
    const password = document.getElementById('formGroupExampleInput2').value;

    // Simple client-side validation
    if (!email || !password) {
        alert('Please enter both email and password');
        return;
    }

    // Send a request to your server
    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({email, password}),
        credentials: "include"
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                // Redirect to dashboard if login is successful
                // store email value in a cookie
                localStorage.setItem("userEmail",email)
                window.location.href = '/dashboard.html';
            } else {
                alert('Invalid credentials. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred. Please try again later.');
        });
}

function logout() {
    fetch('/api/logout', {
        method: 'POST',
        credentials: 'include'
    })
        .then(() => {
            localStorage.removeItem('userEmail');
            window.location.href = '/';
        })
        .catch(error => console.error('Error:', error));
}


document.addEventListener('DOMContentLoaded', function() {
    const userEmail = localStorage.getItem('userEmail');
    if (!userEmail) {
        window.location.href = '/';
    }
});

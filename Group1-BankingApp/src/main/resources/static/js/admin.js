document.addEventListener('DOMContentLoaded', function () {
    // Check if you are admin by alert prompt
    var admin = prompt("Admin password:");
    if (admin !== "1234") {
        alert("You are not an admin");
        window.location.href = '/';
        return;
    }

    // Fetch all customers when the page loads
    fetchAllCustomers();

    // Add an event listener to the fetch button
    document.getElementById('fetchCustomerBtn').addEventListener('click', fetchCustomerById);
});

// Function to fetch all customers from the server
function fetchAllCustomers() {
    fetch('/api/getAllCustomers') // Send a request to the server to get all customers
        .then(response => response.json()) // Convert the response to JSON
        .then(data => {
            const tableBody = document.querySelector('#customerTable tbody');
            tableBody.innerHTML = '';
            data.forEach(customer => {
                const row = document.createElement('tr'); // Create a new table row

                // Create and append the ID cell
                const idCell = document.createElement('td'); // Create a new table cell for the ID
                idCell.textContent = customer.id; // Set the cell's text to the customer's ID
                row.appendChild(idCell); // Add the cell to the row

                // Create and append the Full Name cell
                const fullNameCell = document.createElement('td'); // Create a new table cell for the full name
                fullNameCell.textContent = customer['Full-Name']; // Set the cell's text to the customer's full name
                row.appendChild(fullNameCell); // Add the cell to the row

                // Create and append the Email cell
                const emailCell = document.createElement('td'); // Create a new table cell for the email
                emailCell.textContent = customer.Email; // Set the cell's text to the customer's email
                row.appendChild(emailCell); // Add the cell to the row

                // Create and append the Balance cell
                const balanceCell = document.createElement('td'); // Create a new table cell for the balance
                balanceCell.textContent = customer.Balance; // Set the cell's text to the customer's balance
                row.appendChild(balanceCell); // Add the cell to the row

                // Append the row to the table body
                tableBody.appendChild(row); // Add the row to the table body
            });
        })
        .catch(error => alert(error)); // Show an error message if something goes wrong
}
// Function to fetch a specific customer by ID from the server
function fetchCustomerById() {
    const customerId = document.getElementById('customerId').value;

    fetch(`/api/${customerId}`) // Send a request to the server to get the customer by ID
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    throw new Error(`${response.status}: ${errorData.message}`);
                });
            }
            return response.json();
        })
        .then(customer => {
            document.getElementById('customerDetails').style.display = 'block';
            document.getElementById('custId').textContent = customer.id;
            document.getElementById('custFullName').textContent = customer["Full-Name"];
            document.getElementById('custEmail').textContent = customer.Email;
            document.getElementById('custBalance').textContent = customer.Balance;
            document.getElementById('custAccountNumber').textContent = customer["Account Number"];
            document.getElementById('custAddress').textContent = customer.Address;
            document.getElementById('custDOB').textContent = customer["Date of Birth"];
        })
        .catch(error => {
            document.getElementById('errorDiv').style.display = 'block';
            document.getElementById('errorCode').textContent = error.message.split(': ')[0];
            document.getElementById('errorMessage').textContent = error.message.split(': ')[1];
        });
}

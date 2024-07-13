// This function runs when the document is fully loaded
document.addEventListener('DOMContentLoaded', function () {
    // Fetch customer by email when the page loads
    fetchCustomerByEmail();
        document.getElementById('logout-link').addEventListener('click', function (event) {
            event.preventDefault();
            logout();
        });
});

function fetchCustomerByEmail() {
    // Get the email from the 'userEmail' stored in localStorage
    const email = localStorage.getItem('userEmail');

    if (email) {
        // Send the email to the server using an AJAX request
        fetch('/api/customerByEmail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email: email })
        })
        .then(response => response.json())
        .then(data => {
           // Handle the server response here
            document.getElementById('customer-name').innerText = data['Full-Name'];
            document.getElementById('customer-email').innerText = data['Email'];
            document.getElementById('customer-address').innerText = data['Address'];
            document.getElementById('customer-balance').innerText = data['Balance'];
            document.getElementById('customer-account-number').innerText = data['Account Number'];
            document.getElementById('customer-dob').innerText = data['Date of Birth'];
        })
        .catch(error => {
            console.error("Error fetching customer data:", error);
        });
    } else {
        console.error("No 'userEmail' found in localStorage");
    }
}

function logout() {
    // Clear the user email from localStorage
    localStorage.removeItem('userEmail');

    // Optionally, you can clear the cookies as well if they are used for session management
    document.cookie.split(";").forEach(function(c) {
        document.cookie = c.trim().split("=")[0] + "=;expires=Thu, 01 Jan 1970 00:00:00 UTC;path=/";
    });

    // Redirect to the login page
    window.location.href = '/'; // Adjust the path to your login page as necessary
}
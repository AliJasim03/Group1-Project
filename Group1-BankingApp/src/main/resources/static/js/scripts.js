
// Function to show an error message
/*function showErrorDiv(error) {
    const errorDiv = document.getElementById('errorDiv'); // Get the error message div
    const [statusCode, errorMessage] = error.message.split(': '); // Split the error message into status code and message

    // Set the error code and message in the div
    document.getElementById('errorCode').textContent = statusCode; // Set the text content for the error code
    document.getElementById('errorMessage').textContent = errorMessage; // Set the text content for the error message

    // Display the error div
    errorDiv.style.display = 'block'; // Make the error message div visible
}*/

const loginBtn = document.getElementById('loginBtn');
if (loginBtn) {
    loginBtn.addEventListener('click', handleLogin);
}

function handleLogin() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('pass').value;

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
        body: JSON.stringify({ email, password }),
        credentials: 'include'
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem('userEmail', email);
                window.location.href = '/dashboard';
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
        if (window.location.pathname !== '/') {
            window.location.href = '/';
        }
    }
});


  document.getElementById("togglePassword").addEventListener("click", function () {
      const passwordField = document.getElementById("pass");

      const type =
        passwordField.getAttribute("type") === "password" ? "text" : "password";

      passwordField.setAttribute("type", type);

      this.classList.toggle("bx-hide");

      this.classList.toggle("bx-show");
    });

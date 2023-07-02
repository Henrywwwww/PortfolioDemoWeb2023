function includeHTML() {
    // Get the element with the id "navigator"
    var headerElement = document.getElementById("navigator");

    // Check if the element exists
    if (headerElement) {
        // Create a new XMLHttpRequest object
        var xhttp = new XMLHttpRequest();

        // Define a callback function to handle the response
        xhttp.onreadystatechange = function() {
            // Check if the request is complete and successful
            if (this.readyState == 4 && this.status == 200) {
                // Set the innerHTML of the headerElement to the responseText
                headerElement.innerHTML = this.responseText;
            }
        };

        // Open a GET request to the "navigator.html" file
        xhttp.open("GET", "navigator.html", true);

        // Send the request
        xhttp.send();
    }
}

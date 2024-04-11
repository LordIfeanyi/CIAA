document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('mealSearchForm');
    const searchResults = document.getElementById('searchResults');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const searchInput = document.getElementById('searchInput').value;

        // Make AJAX request to backend endpoint
        fetch('/api/search', {
            method: 'POST',
            body: JSON.stringify({ query: searchInput }),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            // Clear previous search results
            searchResults.innerHTML = '';

            // Display new search results
            data.forEach(result => {
                const listItem = document.createElement('li');
                listItem.textContent = result.name;
                searchResults.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error:', error));
    });
});
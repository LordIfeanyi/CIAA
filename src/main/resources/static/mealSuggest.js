document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('mealSuggestForm');
    const mealSuggestions = document.getElementById('mealSuggestions');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const preferences = document.getElementById('preferences').value;

        // Make AJAX request to backend endpoint
        fetch('/api/suggest', {
            method: 'POST',
            body: JSON.stringify({ preferences: preferences }),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            // Clear previous meal suggestions
            mealSuggestions.innerHTML = '';

            // Display new meal suggestions
            data.forEach(suggestion => {
                const listItem = document.createElement('li');
                listItem.textContent = suggestion.name;
                mealSuggestions.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error:', error));
    });
});
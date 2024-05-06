function clearRadioButtons(name) {
    const checkedRadioButton = document.querySelector('input[name="' + name + '"]:checked');
    checkedRadioButton.checked = false;
}

async function generateList() {

    // get the div to be dynamically populated with items
    const itemList = document.getElementById("searchResults");
    itemList.innerHTML = '';

    // get the values to be appended to the endpoint
    let type = null;
    if (document.querySelector('input[name="type"]:checked') !== null) {
        type = document.querySelector('input[name="type"]:checked').value; // get the type from this html page
    }

    const suggestResults = await fetchSuggestions(`/ciaa/meal-suggest/suggest`, type);

    // print the results to the console
    console.log(suggestResults);

    const results = suggestResults["recipes"];

    // if no results came back
    if (results.length === 0) {
        const noResults = document.createElement("div");
        noResults.textContent = "No suggestions were found. Try reducing the amount of intolerances you have.";
        itemList.appendChild(noResults);
        return;
    }

    results.forEach(result => {

        // make a new list item using the data
        const li = document.createElement("li");
        li.textContent = result.title;
        const dropdownContent = document.createElement("div");
        dropdownContent.className = "dropdown-content";
        dropdownContent.textContent = result.id;
        const saveButton = document.createElement("span");
        saveButton.className = "save-button";
        saveButton.textContent = "Save";

        // make event listeners for the save and dropdown functionality
        saveButton.addEventListener("click", function () { // if the save button is pressed, remove it from the list and save it by posting to our backend via the saveMeal() method
            li.remove();
            saveMeal(result.id); // save method
        });
        li.addEventListener("click", function () {
            li.classList.toggle("dropdown-active");
        });

        // add the components to the li and the li to the list
        li.appendChild(dropdownContent);
        li.appendChild(saveButton);
        itemList.appendChild(li);

    });
}

async function fetchSuggestions(url, type) {
    const response = await fetch(url, {body: type, method: 'POST'});
    return await response.json();
}

// send a POST request to the backend to add this id to the currents user's saved meals
async function saveMeal(id) {
    try {
        const response = await fetch(`/ciaa/savedmeals/${id}`, {
            method: 'POST'
        });
        if (response.ok) {
            console.log('Item saved successfully');
        } else {
            console.error('Failed to save item');
        }
    } catch (error) {
        console.error('Error saving item:', error);
    }
}
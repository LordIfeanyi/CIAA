function clearRadioButtons(name) {
    const checkedRadioButton = document.querySelector('input[name="' + name + '"]:checked');
    checkedRadioButton.checked = false;
}

function clearCheckBoxes() {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = false;
    });
}

async function generateList() {

    // get the div to be dynamically populated with items
    const itemList = document.getElementById("searchResults");
    itemList.innerHTML = '';

    // get the values to be appended to the endpoint
    const query = document.getElementById("query").value;
    let cuisine = null;
    let diet = null;
    let mealType = null;
    const intolerances = [];
    if (document.querySelector('input[name="cuisine"]:checked') !== null) {
        cuisine = document.querySelector('input[name="cuisine"]:checked').value;
    }
    if (document.querySelector('input[name="diet"]:checked') !== null) {
        diet = document.querySelector('input[name="diet"]:checked').value;
    }
    if (document.querySelector('input[name="type"]:checked') !== null) {
        mealType = document.querySelector('input[name="type"]:checked').value;
    }
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    checkboxes.forEach(function (checkbox) {
        intolerances.push(checkbox.value);
    });

    const request = {
        query: query,
        cuisine: cuisine,
        diet: diet,
        mealType: mealType,
        intolerances: intolerances
    }

    // fetch the items by calling the spoonacular endpoint
    const searchResults = await fetchSearchResults("ciaa/meal-search/search", request); // fetch method

    const results = searchResults["recipes"];

    // if no results came back
    if (results.length === 0) {
        const noResults = document.createElement("div");
        noResults.textContent = "No results were found. Try making your search less specific.";
        itemList.appendChild(noResults);
        return;
    }

    results.forEach((result) => {

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
        saveButton.addEventListener("click", function () {
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

async function fetchSearchResults(url, request) {
    console.log("Request Body:" + request);
    const response = await fetch(url, { body: JSON.stringify(request),method: 'POST',
        headers: { 'Content-Type': 'application/json' } });
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

export {appendToList, fetchComplexRecipe};

async function fetchComplexRecipe(id) {
    const response = await fetch(`/ciaa/recipe-information/${id}`);
    return await response.json();
}

function appendToList(result, itemList, document) {
    // make a new list item using the data
    const li = document.createElement("li");
    li.textContent = result.title;
    const dropdownContent = document.createElement("div");
    dropdownContent.className = "dropdown-content";
    dropdownContent.textContent = result["instructions"];
    const saveButton = document.createElement("span");
    saveButton.className = "save-button";
    saveButton.textContent = "Save";

    // make event listeners for the save and dropdown functionality
    saveButton.addEventListener("click", function () { // if the save button is pressed, remove it from the list and save it by posting to our backend via the saveMeal() method
        saveMeal(result.id).then(() => li.remove()); // save method
    });
    li.addEventListener("click", function () {
        li.classList.toggle("dropdown-active");
    });

    // add the components to the li and the li to the list
    li.appendChild(dropdownContent);
    li.appendChild(saveButton);
    itemList.appendChild(li);
}

// send a POST request to the backend to add this id to the currents user's saved meals
async function saveMeal(id) {
    try {
        const response = await fetch(`/ciaa/saved-meals/${id}`, {
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
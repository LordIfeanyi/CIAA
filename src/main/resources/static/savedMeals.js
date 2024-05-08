import {fetchComplexRecipe} from "./common.js";

async function fetchItems() {
    const response = await fetch('/ciaa/saved-meals');
    return await response.json();
}

async function deleteItem(id) {
    try {
        const response = await fetch(`/ciaa/saved-meals/${id}`, {
            method: 'DELETE'
        });
        if (response.ok) {
            console.log('Item deleted successfully');
        } else {
            console.error('Failed to delete item');
        }
    } catch (error) {
        console.error('Error deleting item:', error);
    }
}

window.onload = async function generateList() {

    const itemList = document.getElementById("item-list");
    itemList.innerHTML = '';

    const recipeIDs = await fetchItems();

    if (recipeIDs.length === 0) {
        const noResults = document.createElement("div");
        noResults.textContent = "You currently have no saved meals. Go to 'Meal Search' or 'Meal Suggest' to save meals!";
        noResults.style.color = 'black'; // Red text color
        noResults.style.fontSize = '24px'; // Larger font size
        noResults.style.textAlign = 'center'; // Center-align the text
        noResults.style.width = '100%'; // Full width
        noResults.style.background = 'white';
        noResults.style.padding = '10px'; // Padding for better spacing
        itemList.appendChild(noResults);
        return;
    }

    for (const id of recipeIDs) {
        const data = await fetchComplexRecipe(id);

        const li = document.createElement("li");
        li.textContent = data.title;
        const dropdownContent = document.createElement("div");
        dropdownContent.className = "dropdown-content";
        dropdownContent.textContent = data["instructions"];
        const removeButton = document.createElement("span");
        removeButton.className = "remove-button";
        removeButton.textContent = "Remove";

        removeButton.addEventListener("click", function () {
            deleteItem(id).then(() => li.remove());
        });
        li.addEventListener("click", function () {
            li.classList.toggle("dropdown-active");
        });

        li.appendChild(dropdownContent);
        li.appendChild(removeButton);
        itemList.appendChild(li);
    }
}
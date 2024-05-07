function updateSelectedCount() {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    var selectedCount = checkboxes.length;
    document.getElementById("count").innerText = "(" + selectedCount + ")";
    document.getElementById("count1").innerText = "(" + selectedCount + ")";
}

function buySelected(mes) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    var selectedIds = [];

    checkboxes.forEach(function(checkbox) {
        selectedIds.push(checkbox.value);
    });

    if (selectedIds.length === 0) {
        alert("Please select at least one item to buy.");
        return;
    }

    showConfirmationPopup(mes, function() {
        var selectedIdsString = selectedIds.join(",");

        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "buyCheckbox", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        var params = "selectedIds=" + encodeURIComponent(selectedIdsString);

        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                alert("Thank you! Here we go to checkout!");
                window.location.href = "checkout"; 
            }
        };

        xhttp.send(params);
    });
}


function removeSelected(mes) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    var selectedCount = checkboxes.length;

    if (selectedCount === 0) {
        alert("Please select at least one item to delete.");
        return;
    }

    showConfirmationPopup(mes, function() {
        var ids = [];         
        checkboxes.forEach(function (checkbox) {
            ids.push(checkbox.value);
        });
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "deleteSelected", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        var params = "selectedIds=" + encodeURIComponent(ids.join(","));
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                alert("Deleted Successfully!");               
                location.reload();
            }
        };
        xhttp.send(params); 
    });
}

function showConfirmationPopup(message, confirmCallback) {
    document.getElementById('popupMessage').innerText = message;
    document.getElementById('confirmationPopup').style.display = 'block';

    document.getElementById('confirmBtn').onclick = function() {
        hideConfirmationPopup();
        confirmCallback();
    };

    document.getElementById('cancelBtn').onclick = function() {
        hideConfirmationPopup();
    };
}

function hideConfirmationPopup() {
    document.getElementById('confirmationPopup').style.display = 'none';
}

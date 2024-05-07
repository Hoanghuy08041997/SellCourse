function updateOrderSummary() {
    var total = 0;
    var rows = document.querySelectorAll("tbody tr");

    var orderSummaryBody = document.getElementById("orderSummaryBody");
    orderSummaryBody.innerHTML = '';

    rows.forEach(function(row, index) {
        var id = row.querySelector("td:nth-child(2)").innerText; 
        var quantity = parseInt(row.querySelector("select").value);
        var price = parseFloat(row.querySelector("td:nth-child(7)").innerText);
        var discount = parseFloat(row.querySelector("td:nth-child(6)").innerText);
        var subtotal = (quantity * price) - discount;
        total += subtotal;

        // Add the data to the order summary table
        var newRow = document.createElement('tr');
        newRow.innerHTML = '<td>' + id + '</td>' +
                           '<td>' + quantity + '</td>' +
                           '<td>' + discount + '%</td>' +
                           '<td>$' + subtotal.toFixed(2) + '</td>';
        orderSummaryBody.appendChild(newRow);
    });

    document.getElementById("totalPriceCell").innerText = "$"+ total.toFixed(2);
}


function clearOrderSummary() {
    var summaryRows = document.querySelectorAll("#orderSummaryBody tr");
    summaryRows.forEach(function(row) {
        row.remove();
    });
    document.getElementById("totalPriceCell").innerText = "0.00";
}

function clearAndUpdateOrderSummary() {
    clearOrderSummary();
    updateOrderSummary();
}

function purchase() {
    var rows = document.querySelectorAll("#orderSummaryBody tr");
    var selectedIds = [];

    rows.forEach(function(row) {
        var cells = row.querySelectorAll("td");
        var id = cells[0].innerText;
        var quantity = parseInt(cells[1].innerText);

        if (quantity > 0) {
            selectedIds.push(id);
        }
    });

    var params = "selectedIds=" + selectedIds.join(",");

    var xhttp = new XMLHttpRequest();
    xhttp.open("POST", "purchase", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4) {
            if (this.status === 200) {
                alert("Purchase successful!");
                window.location.href = "orders";
            } else if (this.status === 400) {
                alert("Bad request! Please try again");
            } else if (this.status === 401) {
                alert("Unauthorized! Please login" );             
                window.location.href = "login";
            } else {
                alert("An error occurred while processing the purchase. Please contact to admin.");
            }
        }
    };

    xhttp.send(params);
}

function updateLanguage(language) {
    event.preventDefault();
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "LanguageManager", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var responseText = xhr.responseText;
            alert(responseText);
            location.reload();
        }
    };
    xhr.send("language=" + language);
}
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('bookForm');

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(form);
        const book = {};
        formData.forEach(function (value, key) {
            book[key] = value;
        });

        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8081/OOPServlLab4_war_exploded/books', true);
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onload = function () {
            if (xhr.status === 200) {
                console.log('Данные успешно отправлены');
            } else {
                console.error('Произошла ошибка при обработке запроса:', xhr.statusText);
            }
        };

        xhr.send(JSON.stringify(book));
        this.reset();
    });
});
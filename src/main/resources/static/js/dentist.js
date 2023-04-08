$(document).ready(function () {
    const dentistForm = `
        <form id="dentist-form">
            <input type="hidden" id="dentist-id">
            <div class="mb-3">
                <label for="dentist-name" class="form-label">Name</label>
                <input type="text" class="form-control" id="dentist-name" required>
            </div>
            <div class="mb-3">
                <label for="dentist-lastname" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="dentist-lastname" required>
            </div>
            <div class="mb-3">
                <label for="dentist-license" class="form-label">License</label>
                <input type="text" class="form-control" id="dentist-license" required>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
            <button type="button" class="btn btn-secondary" id="dentist-cancel">Cancel</button>
        </form>
    `;

    const dentistTable = `
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Last Name</th>
                    <th>License</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody id="dentist-list">

                                </tbody>
                            </table>
                        `;

                        function fetchDentists() {
                            $.ajax({
                                url: '/api/dentist',
                                type: 'GET',
                                success: function (dentists) {
                                    let dentistRows = '';
                                    dentists.forEach(dentist => {
                                        dentistRows += `
                                            <tr>
                                                <td>${dentist.id}</td>
                                                <td>${dentist.name}</td>
                                                <td>${dentist.lastName}</td>
                                                <td>${dentist.license}</td>
                                                <td>
                                                    <button class="btn btn-sm btn-info edit-dentist" data-id="${dentist.id}">Edit</button>
                                                    <button class="btn btn-sm btn-danger delete-dentist" data-id="${dentist.id}">Delete</button>
                                                </td>
                                            </tr>
                                        `;
                                    });
                                    $('#dentist-list').html(dentistRows);
                                },
                            });
                        }

                        function resetForm() {
                            $('#dentist-id').val('');
                            $('#dentist-name').val('');
                            $('#dentist-lastname').val('');
                            $('#dentist-license').val('');
                            $('#dentist-cancel').hide();
                        }

                        $('#dentist-form').on('submit', function (event) {
                            event.preventDefault();
                            const dentistData = {
                                id: $('#dentist-id').val(),
                                name: $('#dentist-name').val(),
                                lastName: $('#dentist-lastname').val(),
                                license: $('#dentist-license').val(),
                            };

                            const requestType = dentistData.id ? 'PUT' : 'POST';
                            const requestUrl = dentistData.id
                                ? `/api/dentist/${dentistData.id}`
                                : '/api/dentist';

                            $.ajax({
                                url: requestUrl,
                                type: requestType,
                                contentType: 'application/json',
                                data: JSON.stringify(dentistData),
                                success: function () {
                                    resetForm();
                                    fetchDentists();
                                },
                            });
                        });

                        $('body').on('click', '.edit-dentist', function () {
                            const dentistId = $(this).data('id');
                            $.ajax({
                                url: `/api/dentist/${dentistId}`,
                                type: 'GET',
                                success: function (dentist) {
                                    $('#dentist-id').val(dentist.id);
                                    $('#dentist-name').val(dentist.name);
                                    $('#dentist-lastname').val(dentist.lastName);
                                    $('#dentist-license').val(dentist.license);
                                    $('#dentist-cancel').show();
                                },
                            });
                        });

                        $('body').on('click', '.delete-dentist', function () {
                            const dentistId = $(this).data('id');
                            if (confirm('Do you want to fire this dentist?')) {
                                $.ajax({
                                    url: `/api/dentist/${dentistId}`,
                                    type: 'DELETE',
                                    success: function () {
                                        fetchDentists();
                                    },
                                });
                            }
                        });

                        $('#dentist-cancel').on('click', function () {
                            resetForm();
                        });

                        $('#dentist-form').html(dentistForm);

                           $('#dentist-form').html(dentistForm);
                            $('#dentist-table').html(dentistTable);
                            $('#dentist-cancel').hide();
                            fetchDentists();
                        });

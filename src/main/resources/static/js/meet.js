$(document).ready(function () {
    loadMeets();
    loadPatients();
    loadDentists();

    $('#meet-form').submit(function (event) {
        event.preventDefault();
        saveMeet();
    });
});

function loadMeets() {
    // ...
}

function loadPatients() {
    // ...
}

function loadMeets() {
    $.ajax({
        url: '/api/meet',
        type: 'GET',
        success: function (meets) {
            renderMeets(meets);
        },
        error: function (error) {
            console.error('Error fetching meets:', error);
        }
    });
}

function renderMeets(meets) {
    const meetList = $('#meet-list');
    meetList.empty();

    meets.forEach(meet => {
        meetList.append(`
            <tr>
                <td>${meet.id}</td>
                <td>${meet.title}</td>
                <td>${meet.date}</td>
                <td>${meet.patient.name} ${meet.patient.lastName}</td>
                <td>${meet.dentist.name} ${meet.dentist.lastName}</td>
                <td>
                    <button class="btn btn-sm btn-info" onclick="editMeet(${meet.id})">Edit</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteMeet(${meet.id})">Delete</button>
                </td>
            </tr>
        `);
    });
}

function loadPatients() {
    $.ajax({
        url: '/api/patient',
        type: 'GET',
        success: function (patients) {
            const patientSelect = $('#meet-patient');
            patientSelect.empty();

            patients.forEach(patient => {
                patientSelect.append(`
                    <option value="${patient.id}">${patient.name} ${patient.lastName}</option>
                `);
            });
        },
        error: function (error) {
            console.error('Error fetching patients:', error);
        }
    });
}

function loadDentists() {
    $.ajax({
        url: '/api/dentist',
        type: 'GET',
        success: function (dentists) {
            const dentistSelect = $('#meet-dentist');
            dentistSelect.empty();

            dentists.forEach(dentist => {
                dentistSelect.append(`
                    <option value="${dentist.id}">${dentist.name} ${dentist.lastName}</option>
                `);
            });
        },
        error: function (error) {
            console.error('Error fetching dentists:', error);
        }
    });
}

function clearForm() {
    $('#meet-id').val('');
    $('#meet-title').val('');
    $('#meet-date').val('');
    $('#meet-patient').val('');
    $('#meet-dentist').val('');
}

function editMeet(meetId) {
    $.ajax({
        url: `/api/meet/${meetId}`,
        type: 'GET',
        success: function (meet) {
            $('#meet-id').val(meet.id);
            $('#meet-title').val(meet.title);
            $('#meet-date').val(meet.date);
            $('#meet-patient').val(meet.patient.id);
            $('#meet-dentist').val(meet.dentist.id);

            $('#meetModal').modal('show');
        },
        error: function (error) {
            console.error('Error fetching meet:', error);
        }
    });
}

function deleteMeet(meetId) {
    $.ajax({
        url: `/api/meet/${meetId}`,
        type: 'DELETE',
        success: function () {
            loadMeets();
        },
        error: function (error) {
            console.error('Error deleting meet:', error);
        }
    });
}

function saveMeet() {
    const meetId = $('#meet-id').val();
    const meetTitle = $('#meet-title').val();
    const meetDate = $('#meet-date').val();
    const meetPatientId = $('#meet-patient').val();
    const meetDentistId = $('#meet-dentist').val();

    const meetData = {
        title: meetTitle,
        date: meetDate,
        patient: {
            id: meetPatientId
        },
        dentist: {
            id: meetDentistId
        }
    };

    if (meetId) {
        $.ajax({
            url: `/api/meet/${meetId}`,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(meetData),
            success: function () {
                $('#meetModal').modal('hide');
                loadMeets();
            },
            error: function (error) {
                console.error('Error updating meet:', error);
            }
        });
    } else {
        $.ajax({
            url: '/api/meet',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(meetData),
            success: function () {
                $('#meetModal').modal('hide');
                loadMeets();
            },
            error: function (error) {
                console.error('Error creating meet:', error);
            }
        });
    }
}


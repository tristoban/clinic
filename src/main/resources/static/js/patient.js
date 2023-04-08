$(document).ready(function () {
    getAllPatients();

    $('#patient-form').on('submit', function (e) {
        e.preventDefault();
        const id = $('#patient-id').val();
        const patient = {
            name: $('#patient-name').val(),
            lastName: $('#patient-last-name').val(),
            dni: $('#patient-dni').val(),
            entryDate: $('#patient-entry-date').val(),
            address: $('#patient-address').val()
        };

        if (id) {
            updatePatient(id, patient);
        } else {
            createPatient(patient);
        }

        $('#patientModal').modal('hide');
    });

    $('#patientModal').on('hidden.bs.modal', function () {
        clearForm();
    });
});

function getAllPatients() {
    $.ajax({
        url: '/api/patient',
        method: 'GET',
        success: function (patients) {
            populateTable(patients);
        }
    });
}

function createPatient(patient) {
    $.ajax({
        url: '/api/patient',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(patient),
        success: function () {
            getAllPatients();
        }
    });
}

function updatePatient(id, patient) {
    $.ajax({
        url: '/api/patient/' + id,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(patient),
        success: function () {
            getAllPatients();
        }
    });
}

function deletePatient(id) {
    $.ajax({
        url: '/api/patient/' + id,
        method: 'DELETE',
        success: function () {
            getAllPatients();
        }
    });
}

function populateTable(patients) {
    const tbody = $('#patients-table tbody');
    tbody.empty();

    patients.forEach(patient => {
        const row = $('<tr>');
        row.append($('<td>').text(patient.id));
        row.append($('<td>').text(patient.name));
        row.append($('<td>').text(patient.lastName));
        row.append($('<td>').text(patient.dni));
        row.append($('<td>').text(patient.entryDate));
        row.append($('<td>').text(patient.address));

        const actions = $('<td>');
        const editButton = $('<button>').addClass('btn btn-primary').text('Edit');
        editButton.on('click', function () {
            editPatient(patient);
        });

        const deleteButton = $('<button>').addClass('btn btn-danger').text('Delete');
        deleteButton.on('click', function () {
            deletePatient(patient.id);
        });

        actions.append(editButton).append
        actions.append(deleteButton);
        row.append(actions);

        tbody.append(row);
    });
}

function editPatient(patient) {
    $('#patient-id').val(patient.id);
    $('#patient-name').val(patient.name);
    $('#patient-last-name').val(patient.lastName);
    $('#patient-dni').val(patient.dni);
    $('#patient-entry-date').val(patient.entryDate);
    $('#patient-address').val(patient.address);
    $('#patientModal').modal('show');
}

function clearForm() {
    $('#patient-form')[0].reset();
    $('#patient-id').val('');
}


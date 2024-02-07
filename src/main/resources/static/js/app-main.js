$(document).ready(function () {

  if ($("#is_game_over").val() !== "true") {
    $(".board-row-tile.available").click(function (event) {
      $("#tile_id").val(event.target.id);
      $("#form_mark_tile").submit();
    });
  }

  $("#btn-new-game").click(function (event) {
    var number = $("#board_size").val().trim();
    var toastBody = $("#toastBody");

    if (number < 3) {
      function showToast() {
        var toast = new bootstrap.Toast(document.getElementById('toastMessage'));
        toast.show();
      }

      toastBody.text("Number must have at least 3 digits.");
      showToast(); // Show toast with validation message
      event.preventDefault();// Prevent form submission
      console.log('kena validasi : ', number)
    }else {
        toastBody.text("");
        $("#new_game").val("yes");
        $("#form_mark_tile").submit();
      console.log('tidak kena validasi : ', number)
    }
  });

  $("#link-user-logout").click(function (event) {
    $("#form-logout").submit();
  });
});

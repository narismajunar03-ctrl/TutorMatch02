let emailExists = false;
let usernameExists = false;
let emailTimeout;
let usernameTimeout;

/* =========================
   STEP NAVIGATION
========================= */

window.nextStep = function (currentStep) {
  if (!validateStep(currentStep)) return;

  const nextStepNum = currentStep + 1;

  const currentStepElement = document.querySelector(`.form-step[data-step="${currentStep}"]`);
  const currentStepIndicator = document.querySelector(`.step[data-step="${currentStep}"]`);

  if (currentStepElement) currentStepElement.classList.remove("active");
  if (currentStepIndicator) currentStepIndicator.classList.remove("active");

  document.querySelectorAll(".step").forEach(step => {
    const stepNum = Number(step.dataset.step);
    if (stepNum < nextStepNum) step.classList.add("completed");
  });

  const nextStepElement = document.querySelector(`.form-step[data-step="${nextStepNum}"]`);
  const nextStepIndicator = document.querySelector(`.step[data-step="${nextStepNum}"]`);

  if (nextStepElement) nextStepElement.classList.add("active");
  if (nextStepIndicator) nextStepIndicator.classList.add("active");

  window.scrollTo({ top: 0, behavior: "smooth" });
};

window.prevStep = function (currentStep) {
  const currentStepElement = document.querySelector(`.form-step[data-step="${currentStep}"]`);
  const currentStepIndicator = document.querySelector(`.step[data-step="${currentStep}"]`);

  if (currentStepElement) currentStepElement.classList.remove("active");
  if (currentStepIndicator) currentStepIndicator.classList.remove("active");

  if (currentStepElement) {
    currentStepElement.querySelectorAll(".error-message")
      .forEach(e => e.classList.remove("show"));

    currentStepElement.querySelectorAll(".form-group")
      .forEach(g => g.classList.remove("error"));
  }

  const prevStepNum = currentStep - 1;

  const prevStepElement = document.querySelector(`.form-step[data-step="${prevStepNum}"]`);
  const prevStepIndicator = document.querySelector(`.step[data-step="${prevStepNum}"]`);

  if (prevStepElement) prevStepElement.classList.add("active");
  if (prevStepIndicator) prevStepIndicator.classList.add("active");

  window.scrollTo(0, 0);
};

/* =========================
   VALIDATION
========================= */

function validateStep(step) {
  let isValid = true;

  const getVal = (id) => document.getElementById(id)?.value.trim() || "";

  const showErr = (errorId, inputId, msg) => {
    const errorMsg = document.getElementById(errorId);
    const inputField = document.getElementById(inputId);

    if (errorMsg) {
      if (msg) errorMsg.innerText = msg;
      errorMsg.classList.add("show");
    }

    const container = inputField?.closest(".form-group");
    if (container) container.classList.add("error");

    return false;
  };

  const hasFiles = (id) => document.getElementById(id)?.files?.length > 0;

  if (step === 1) {
    const firstName = getVal("firstName");
    const lastName = getVal("lastName");
    const email = getVal("email");
    const contactNumber = getVal("contactNumber");
    const street = getVal("street");
    const barangay = getVal("barangay");
    const city = getVal("city");
    const zipCode = getVal("zipCode");

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!firstName) isValid = showErr("firstNameError", "firstName");
    if (!lastName) isValid = showErr("lastNameError", "lastName");
    if (!emailRegex.test(email)) isValid = showErr("emailError", "email");
    if (contactNumber.length < 10) isValid = showErr("contactError", "contactNumber");
    if (!street) isValid = showErr("streetError", "street");
    if (!barangay) isValid = showErr("barangayError", "barangay");
    if (!city) isValid = showErr("cityError", "city");
    if (!zipCode || zipCode.length < 4) isValid = showErr("zipCodeError", "zipCode");

    if (emailExists) isValid = showErr("emailError", "email", "Email already exists!");
    if (usernameExists) isValid = showErr("usernameError", "username", "Username already exists!");
  }

  if (step === 2) {
    const username = getVal("username");
    const password = getVal("password");
    const confirmPassword = getVal("confirmPassword");

    if (username.length < 3 || username.length > 20)
      isValid = showErr("usernameError", "username");

    if (password.length < 8)
      isValid = showErr("passwordError", "password");

    if (password !== confirmPassword)
      isValid = showErr("confirmPasswordError", "confirmPassword");
  }

  if (step === 3) {
    const description = getVal("description");

    if (description.length < 20)
      isValid = showErr("descriptionError", "description");

    if (!hasFiles("certificate"))
      isValid = showErr("certificateError", "certificate");

    if (!hasFiles("license"))
      isValid = showErr("licenseError", "license");

    if (!hasFiles("additionalDocs"))
      isValid = showErr("additionalDocsError", "additionalDocs");
  }

  return isValid;
}

/* =========================
   PASSWORD STRENGTH FUNCTION
========================= */

window.checkPasswordStrength = function () {
  const password = document.getElementById("password");
  const strengthBar = document.getElementById("strengthBar");
  const strengthText = document.getElementById("strengthText");

  if (!password || !strengthBar || !strengthText) return;

  const val = password.value;
  let strength = "Weak";
  let percentage = 30;

  if (val.length >= 8) {
    if (/[A-Z]/.test(val) && /\d/.test(val) && /[!@#$%^&*]/.test(val)) {
      strength = "Strong";
      percentage = 100;
    } else if (/[A-Z]/.test(val) && /\d/.test(val)) {
      strength = "Medium";
      percentage = 70;
    } else {
      strength = "Fair";
      percentage = 50;
    }
  }

  strengthBar.style.width = percentage + "%";
  strengthBar.style.backgroundColor =
    strength === "Strong" ? "#22c55e" :
    strength === "Medium" ? "#f59e0b" :
    strength === "Fair" ? "#f59e0b" : "#ef4444";

  strengthText.innerText = "Password strength: " + strength;
};

/* =========================
   TOGGLE PASSWORD VISIBILITY
========================= */

window.togglePasswordVisibility = function () {
  const password = document.getElementById("password");
  const confirmPassword = document.getElementById("confirmPassword");
  const checkbox = document.getElementById("showPassword");

  if (password) password.type = checkbox.checked ? "text" : "password";
  if (confirmPassword) confirmPassword.type = checkbox.checked ? "text" : "password";
};

/* =========================
   FILE UPLOAD HANDLERS
========================= */

function initializeFileUploads() {
  const fileInputs = [
    { id: "certificate", listId: "certificateList" },
    { id: "license", listId: "licenseList" },
    { id: "additionalDocs", listId: "additionalDocsList" }
  ];

  fileInputs.forEach(({ id, listId }) => {
    const input = document.getElementById(id);
    if (input) {
      input.addEventListener("change", function () {
        displayFiles(id, listId);
      });
    }
  });
}

function displayFiles(inputId, listId) {
  const input = document.getElementById(inputId);
  const list = document.getElementById(listId);
  if (!input || !list) return;

  list.innerHTML = "";

  if (input.files.length > 0) {
    Array.from(input.files).forEach((file, index) => {
      const fileItem = document.createElement("div");
      fileItem.className = "file-item";
      fileItem.innerHTML = `
        <span>${file.name} (${(file.size / 1024).toFixed(2)} KB)</span>
        <span class="file-item-remove" onclick="removeFile('${inputId}', ${index})">Remove</span>
      `;
      list.appendChild(fileItem);
    });
  }
}

window.removeFile = function (inputId, index) {
  const input = document.getElementById(inputId);
  if (!input) return;

  const dataTransfer = new DataTransfer();
  Array.from(input.files).forEach((file, i) => {
    if (i !== index) {
      dataTransfer.items.add(file);
    }
  });
  input.files = dataTransfer.files;

  const listId =
    inputId === "certificate" ? "certificateList" :
    inputId === "license" ? "licenseList" : "additionalDocsList";

  displayFiles(inputId, listId);
};

/* =========================
   HANDLE FORM SUBMISSION
========================= */

window.handleTutorRegistration = function (event) {
  event.preventDefault();

  if (!validateStep(3)) return;

  const formData = new FormData(document.getElementById("tutorRegistrationForm"));

  fetch("/users/tutor-registration", {
    method: "POST",
    body: formData
  })
    .then((response) => {
      if (response.ok) {
        alert("Tutor registration successful! You can now login.");
        window.location.href = "/login";
      } else {
        alert("Registration failed. Please try again.");
      }
    })
    .catch((error) => {
      console.error("Registration error:", error);
      alert("An error occurred during registration.");
    });
};

/* =========================
   INITIALIZATION
========================= */

document.addEventListener("DOMContentLoaded", function () {

  // Initialize file upload listeners
  initializeFileUploads();

  /* =========================
     EMAIL CHECK
  ========================= */

  const emailEl = document.getElementById("email");

  if (emailEl) {
    emailEl.addEventListener("input", function () {
      clearTimeout(emailTimeout);

      emailTimeout = setTimeout(async () => {
        const email = this.value.trim();
        if (email.length < 5) return;

        try {
          console.log("Checking email:", email);
          const res = await fetch(`/check/tutor-email?email=${encodeURIComponent(email)}`);
          console.log("Email check response status:", res.status);

          if (!res.ok) {
            console.error("Email check failed with status:", res.status);
            return;
          }

          const data = await res.json();
          console.log("Email check result:", data);
          emailExists = data;

          const error = document.getElementById("emailError");

          if (emailExists === true) {
            error.innerText = "Email already exists!";
            error.classList.add("show");
          } else {
            error.classList.remove("show");
          }
        } catch (e) {
          console.error("Email check error:", e);
        }
      }, 500);
    });
  }

  /* =========================
     USERNAME CHECK
  ========================= */

  const usernameEl = document.getElementById("username");

  if (usernameEl) {
    usernameEl.addEventListener("input", function () {
      clearTimeout(usernameTimeout);

      usernameTimeout = setTimeout(async () => {
        const value = this.value.trim();
        if (value.length < 3) return;

        try {
          console.log("Checking username:", value);
          const res = await fetch(`/check/tutor-username?username=${encodeURIComponent(value)}`);
          console.log("Username check response status:", res.status);

          if (!res.ok) {
            console.error("Username check failed with status:", res.status);
            return;
          }

          const data = await res.json();
          console.log("Username check result:", data);
          usernameExists = data;

          const error = document.getElementById("usernameError");

          if (usernameExists === true) {
            error.innerText = "Username already exists!";
            error.classList.add("show");
          } else {
            error.classList.remove("show");
          }
        } catch (e) {
          console.error("Username check error:", e);
        }
      }, 500);
    });
  }

  /* =========================
     CLEAR ERRORS ON INPUT
  ========================= */

  document.querySelectorAll("input[type='text'], input[type='email'], input[type='tel'], textarea").forEach(input => {
    input.addEventListener("focus", function () {
      const container = this.closest(".form-group");
      if (container) {
        container.classList.remove("error");
        const errorMsg = container.querySelector(".error-message");
        if (errorMsg) errorMsg.classList.remove("show");
      }
    });
  });

});
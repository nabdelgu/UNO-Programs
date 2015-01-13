package gov.louisiana.wlf.swordfish.model;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FishermanRegistrationActivity extends Activity implements TextWatcher {

    private long _id;

    public static Spinner LICENSE_TYPE;
    public static EditText LOGIN_USERNAME;
    public static EditText PASSWORD;
    public static EditText CONFIRM_PASSWORD;
    public static EditText EMAIL;
    public static EditText FIRST_NAME;
    public static EditText LAST_NAME;
    public static EditText STREET_ADDRESS;
    public static EditText STREET_ADDRESS_LINE_2;
    public static EditText CITY;
    AutoCompleteTextView STATE;
    public static EditText ZIP;
    public static EditText PHONE_NUMBER;
    public static EditText FISHING_LICENSE;
    public static EditText CONFIRM_FISHING_LICENSE;


    String state[]={
           "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado" , "Connecticut" ,"Delaware","Florida","Georgia","Hawaii","Idaho","Illinois" ,"Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana" ,"Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania" ,"Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"
    };


    Button submitApplication;

    // zip code validation
    EditText zipText;


    //password validation
    EditText passwordLength;
    EditText confirmPasswordValidation;



    // fishing license validation
    EditText licenseNumber;
    EditText confirmLicenseNumber;

    // login username validation
    TextView loginUsernameValidate;

    // password validation
    TextView validatePassword;

    //first name validation
    TextView firstNameValidation;

    //last name validation
    TextView lastNameValidation;

    // street address validation
    TextView streetAddressValidation;

    //city validation
    TextView cityValidation;

    // state validation
    TextView stateValidation;

    // zip validation
    TextView zipValidation;

    // phone number validation
    TextView phoneNumberValidation;

    // license number validation
    TextView licenseNumberError;

   public void setid(long id) {
        _id = id;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recreationalangler_main);

        System.out.println("Initializing main activity");

        STATE = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewState);

        STATE.addTextChangedListener(this);
        STATE.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, state));

        // loginUsername
        //final TextView loginUsernameText = (TextView) findViewById(R.id.textLoginUsername);

        Spinner licenseTypeSpinner = (Spinner) findViewById(R.id.spinner);
        // zip
        zipText = (EditText) findViewById(R.id.editZip);
        zipText.addTextChangedListener(zipWatcher);

        // password
        validatePassword = (TextView) findViewById(R.id.textConfirmPasswordText);
        passwordLength = (EditText) findViewById(R.id.editPasswordField);
        confirmPasswordValidation = (EditText) findViewById(R.id.editConfirmPassword);
        // licenseNumber
        licenseNumber = (EditText) findViewById(R.id.editSaltwaterFishingLicense);
        // confirm license
        confirmLicenseNumber = (EditText) findViewById(R.id.editConfirmLicense);

        licenseNumberError = (TextView) findViewById(R.id.textLDWFValidate);

       // loginUsername
        loginUsernameValidate = (TextView) findViewById(R.id.textLoginUsernameValidation);
        // first name
        firstNameValidation = (TextView) findViewById(R.id.textFirstNameValidation);
        // last name
        lastNameValidation = (TextView) findViewById(R.id.textLastNameValidation);
        // street address validation
        streetAddressValidation = (TextView) findViewById(R.id.textStreetAddress);
        // city
        cityValidation = (TextView) findViewById(R.id.textCityValidation);
        // state
        stateValidation = (TextView) findViewById(R.id.textStateValidation);
        // phone number
        phoneNumberValidation = (TextView) findViewById(R.id.textPhoneNumberValidation);
        //zip
        zipValidation = (TextView) findViewById(R.id.textZipValidation);

        //text change listeners
        passwordLength.addTextChangedListener(passwordStrength);
        zipText.addTextChangedListener(zipWatcher);

        //spinner
        ArrayAdapter<CharSequence> licenseTypeAdapter = ArrayAdapter.createFromResource(this, R.array.license_type, android.R.layout.simple_spinner_item);
        licenseTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        licenseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Do nothing.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing.
            }
        });
        licenseTypeSpinner.setAdapter(licenseTypeAdapter);


        // get text from edit text fields

        LICENSE_TYPE = licenseTypeSpinner;
        LOGIN_USERNAME = (EditText) findViewById(R.id.editLoginUsername);
        PASSWORD = (EditText) findViewById(R.id.editPasswordField);
        CONFIRM_PASSWORD = (EditText) findViewById(R.id.editFieldConfirmPassword);
        EMAIL = (EditText) findViewById(R.id.editEmail);
        FIRST_NAME = (EditText) findViewById(R.id.editName);
        LAST_NAME = (EditText) findViewById(R.id.editText);
        STREET_ADDRESS = (EditText) findViewById(R.id.editStreetAddress);
        STREET_ADDRESS_LINE_2 = (EditText) findViewById(R.id.editStreetAddressLine2);
        CITY = (EditText) findViewById(R.id.editCity);
        STATE = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewState);
        ZIP = (EditText) findViewById(R.id.editZip);
        PHONE_NUMBER = (EditText) findViewById(R.id.editPhoneNumber);
        FISHING_LICENSE = (EditText) findViewById(R.id.editSaltwaterFishingLicense);
        CONFIRM_FISHING_LICENSE = (EditText) findViewById(R.id.editConfirmLicense);


        submitApplication = (Button) findViewById(R.id.buttonSubmitApplication);

        // on click listener
        submitApplication.setOnClickListener(new OnClickListener() {


            public void onClick(View view) {
                boolean error = false;

                String licenseCategory = getIntent().getExtras().getString("type");
                String licenseType = LICENSE_TYPE.getSelectedItem().toString();
                String loginUsername = LOGIN_USERNAME.getText().toString();
                String password = PASSWORD.getText().toString();
                String confirmPassword = CONFIRM_PASSWORD.getText().toString(); //not put in database
                String email = EMAIL.getText().toString();
                String firstName = FIRST_NAME.getText().toString();
                String lastName = LAST_NAME.getText().toString();
                String streetAddress = STREET_ADDRESS.getText().toString();
                String streetAddressLine2 = STREET_ADDRESS_LINE_2.getText().toString();
                String city = CITY.getText().toString();
                String state = STATE.getText().toString();
                String zip = ZIP.getText().toString();
                String phoneNumber = PHONE_NUMBER.getText().toString();
                String fishingLicenseNumber = FISHING_LICENSE.getText().toString();
                String confirmFishingLicenseNumber = CONFIRM_FISHING_LICENSE.getText().toString();//not put in database


                System.out.println("license category " + licenseCategory);

                // loginUsername validation


                boolean usernameNotPresent = loginUsername.length() == 0;
                boolean usernameNotLongEnough = loginUsername.length() < 6;

                if (usernameNotPresent) {
                    loginUsernameValidate.setTextColor(Color.RED);
                    loginUsernameValidate.setTextSize(9);
                    loginUsernameValidate.setText("* The Login Username: field is required.");
                } else if (usernameNotLongEnough) {
                    loginUsernameValidate.setTextColor(Color.RED);
                    loginUsernameValidate.setTextSize(9);
                    loginUsernameValidate.setText("* The Login Username: username not long enough.");
                }


                // first name validation
                boolean firstNameNotPresent = firstName.length() == 0;

                if (firstNameNotPresent) {
                    firstNameValidation.setTextColor(Color.RED);
                    firstNameValidation.setTextSize(9);
                    firstNameValidation.setText("* The First Name: field is required.");
                }

                // last name validation

                boolean lastNameNotPresent = lastName.length() == 0;

                if (lastNameNotPresent) {
                    lastNameValidation.setTextColor(Color.RED);
                    lastNameValidation.setTextSize(9);
                    lastNameValidation.setText("* The First Name: field is required.");
                }
                // street address validation
                boolean streetAddressNotPresent = streetAddress.length() == 0;

                if(streetAddressNotPresent) {
                    streetAddressValidation.setTextColor(Color.RED);
                    streetAddressValidation.setTextSize(9);
                    streetAddressValidation.setText("* The First Name: field is required.");
                }
                // city validation

                boolean cityNotPresent = city.length() == 0;

                if (cityNotPresent) {
                    cityValidation.setTextColor(Color.RED);
                    cityValidation.setTextSize(9);
                    cityValidation.setText("* The City: field is required.");
                }

                // state validation

                boolean stateNotPresent = state.length() == 0;

                if (stateNotPresent) {
                    stateValidation.setTextColor(Color.RED);
                    stateValidation.setTextSize(9);
                    stateValidation.setText("* The State: field is required.");
                }

                // zip validation

                boolean zipNotPresent = zip.length() == 0;
                boolean zipValid = zip.length() == 5;

                if (zipNotPresent) {
                    zipValidation.setTextColor(Color.RED);
                    zipValidation.setTextSize(9);
                    zipValidation.setText("* The Zip: field is required.");
                } else if (!zipValid) {
                    zipValidation.setTextColor(Color.RED);
                    zipValidation.setTextSize(9);
                    zipValidation.setText("* The Zip: not a valid zip code.");
                }

                // phone number validation

                boolean phoneNumberNotPresent = phoneNumber.length() == 0;
                boolean phoneNumberValid = phoneNumber.length() == 10;

                if (phoneNumberNotPresent) {
                    phoneNumberValidation.setTextColor(Color.RED);
                    phoneNumberValidation.setTextSize(9);
                    phoneNumberValidation.setText("* The phone number: field is required.");
                } else if (!phoneNumberValid) {
                    phoneNumberValidation.setTextColor(Color.RED);
                    phoneNumberValidation.setTextSize(9);
                    phoneNumberValidation.setText("* The phone number: field is not valid.");
                }

                // password validation
                boolean passwordPresent = password.length() == 0;
                boolean passwordLongEnough = password != null && password.length() >= 6;
                boolean passwordIsConfirmed = password != null && confirmPassword != null && (password.equals(confirmPassword));


                if (passwordPresent) {
                    validatePassword.setTextColor(Color.RED);
                    validatePassword.setTextSize(9);
                    validatePassword.setText("* Password: must be present.");
                    error = true;
                } else if (!passwordLongEnough) {
                    validatePassword.setTextColor(Color.RED);
                    validatePassword.setText("* Password: is too short.");
                    error = true;
                } else if (!passwordIsConfirmed) {
                    validatePassword.setTextColor(Color.RED);
                    validatePassword.setText("* Passwords: do not match.");
                    error = true;

                } else if (passwordIsConfirmed) {
                    validatePassword.setText("");
                }

                // license number on click validation

                boolean licenseValidLength = fishingLicenseNumber.length() == 10;
                boolean licenseNumbersTheSame = fishingLicenseNumber.equals(confirmFishingLicenseNumber);

                if (licenseValidLength && licenseValidLength) {
                    licenseNumberError.setTextSize(9);
                    licenseNumberError.setTextColor(Color.RED);
                    licenseNumberError.setText("");
                    error = false;

                } else if(licenseNumbersTheSame && !licenseValidLength){
                    licenseNumberError.setTextSize(9);
                    licenseNumberError.setTextColor(Color.RED);
                    licenseNumberError.setText("* License number: is too short.");
                }
                else if(!licenseNumbersTheSame && licenseValidLength){
                    licenseNumberError.setTextSize(9);
                    licenseNumberError.setTextColor(Color.RED);
                    licenseNumberError.setText("* License number: do not match.");
                }
                else{
                    licenseNumberError.setTextSize(9);
                    licenseNumberError.setTextColor(Color.RED);
                    licenseNumberError.setText("* License number: invalid license number.");
                }




               // do if valid application
            boolean validApplication = !usernameNotPresent && !usernameNotLongEnough && !firstNameNotPresent && !lastNameNotPresent && !cityNotPresent && !stateNotPresent && !streetAddressNotPresent && zipValid && phoneNumberValid && passwordIsConfirmed && licenseValidLength && licenseNumbersTheSame;

                if (validApplication) {
                    Fisherman fisherman = new Fisherman();
                   // MainScreen mainScreen;
                    // text database
                    fisherman.setLicenseCategory(licenseCategory);
                    fisherman.setLicenseType(licenseType);
                    fisherman.setLoginUsername(loginUsername);
                    fisherman.setPassword(password);
                    fisherman.setEmail(email);
                    fisherman.setFirstName(firstName);
                    fisherman.setLastName(lastName);
                    fisherman.setStreetAddress(streetAddress);
                    fisherman.setStreetAddressLine2(streetAddressLine2);
                    fisherman.setCity(city);
                    fisherman.setState(state);
                    fisherman.setZip(zip);
                    fisherman.setPhoneNumber(phoneNumber);
                    fisherman.setFishingLicenseNumber(fishingLicenseNumber);

                    fisherman.save();
                    /*
                    if (!usernameNotPresent) {
                        loginUsernameValidate.setTextColor(Color.RED);
                        loginUsernameValidate.setTextSize(9);
                        loginUsernameValidate.setText("");
                    }
                    else if (!lastNameNotPresent) {
                        lastNameValidation.setTextColor(Color.RED);
                        lastNameValidation.setTextSize(9);
                        lastNameValidation.setText("");
                    }
                    else if(!streetAddressNotPresent) {
                        streetAddressValidation.setTextColor(Color.RED);
                        streetAddressValidation.setTextSize(9);
                        streetAddressValidation.setText("");
                    }
                    else if (!cityNotPresent) {
                        cityValidation.setTextColor(Color.RED);
                        cityValidation.setTextSize(9);
                        cityValidation.setText("");
                    }
                    else if (!stateNotPresent) {
                        stateValidation.setTextColor(Color.RED);
                        stateValidation.setTextSize(9);
                        stateValidation.setText("");
                    }
                    else if (zipValid) {
                        zipValidation.setTextColor(Color.RED);
                        zipValidation.setTextSize(9);
                        zipValidation.setText("");
                    }
                    else if (phoneNumberValid) {
                        phoneNumberValidation.setTextColor(Color.RED);
                        phoneNumberValidation.setTextSize(9);
                        phoneNumberValidation.setText("");
                    }
                    */


                    loginUsernameValidate.setText("");
                    validatePassword.setText("");
                    firstNameValidation.setText("");
                    lastNameValidation.setText("");
                    streetAddressValidation.setText("");
                    cityValidation.setText("");
                    stateValidation.setText("");
                    zipValidation.setText("");
                    phoneNumberValidation.setText("");
                    licenseNumberError.setText("");


                    Toast.makeText(getApplicationContext(), "Your application has been submitted", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "Invalid application", Toast.LENGTH_SHORT).show();

                    }


            }


        });


    }


    @Override
    protected void onStart() {
        System.out.println("--------------STARTING------------");
        super.onStart();

    }

    private final TextWatcher zipWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            zipValidation.setText("");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            if(editable.length()< 5) {
                zipValidation.setTextColor(Color.RED);
                zipValidation.setTextSize(9);
                zipValidation.setText("* The Zip: not a valid zip code.");
                zipValidation.postDelayed(new Runnable() {
                    public void run() {
                        zipValidation.setText("");
                    }
                }, 10000);

            }

            else if(editable.length() == 5) {
                zipValidation.setTextColor(Color.parseColor("#39CD13"));;
                zipValidation.setText("* Valid zip");
                zipValidation.postDelayed(new Runnable() {
                    public void run() {
                        zipValidation.setText("");
                    }
                }, 10000);

            }

        }

    };

    private TextWatcher passwordStrength = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            validatePassword.setText("");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void afterTextChanged(final Editable editable) {

            if(editable.length() < 6) {

                validatePassword.setTextColor(Color.RED);
                validatePassword.setTextSize(9);
                validatePassword.setText("* Password too short");

            }else if(editable.length() >= 6 && editable.length() <= 8 ){

                validatePassword.setTextColor(Color.RED);
                validatePassword.setTextSize(9);
                validatePassword.setText("* Password Strength: Weak");
                validatePassword.postDelayed(new Runnable() {
                    public void run() {
                validatePassword.setText("");
                    }
                }, 10000);
            }
            else if(editable.length() >= 11 && editable.length() <= 13 ){

                validatePassword.setTextColor(Color.GREEN);
                validatePassword.setTextSize(9);
                validatePassword.setText("* Password Strength: Strong");
                validatePassword.postDelayed(new Runnable() {
                    public void run() {
                        validatePassword.setText("");
                    }
                }, 10000);
            }

            else if(editable.length() >= 8 && editable.length() <= 11 ){

                validatePassword.setTextColor(Color.parseColor("#FF8400"));
                validatePassword.setTextSize(9);
                validatePassword.setText("* Password Strength: Medium");
                validatePassword.postDelayed(new Runnable() {
                    public void run() {
                        validatePassword.setText("");
                    }
                }, 10000);
            }


        }
    };

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
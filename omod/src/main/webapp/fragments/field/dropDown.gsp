<p class="required">
    <label>
        ${config.label}
        <span>(${ ui.message("emr.formValidation.messages.requiredField.label") })</span>
    </label>
    <select name="${config.formFieldName}" size="3">
        <option value=""></option>
        <option value ="male">Male</option>
        <option value ="female">Female</option>
    </select>
    <span class="field-error"></span>
</p>

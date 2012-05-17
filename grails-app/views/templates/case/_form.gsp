<table>
	<tr>
		<td>caption</td>
		<td><g:textField name="plaintiffCaption" value="${plaintiffCaption}" /></td>
	</tr>
	<tr>
		<td>image</td>
		<td><input type="file" name="file" /></td>
	</tr>
	<tr>
		<td>defendant</td>
		<td>
			<g:textField name="defendantName" value="${defendantName}" />
			<r:img dir="images" file="spinner.gif" id="defendantSpinner" style="display:none;" />
		</td>
	</tr>
	<tr>
		<td><input type="button" value="cancel" /></td>
		<td><input type="submit" value="create" /></td>
	</tr>
</table>
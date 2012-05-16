<table>
	<tr>
		<td>caption</td>
		<td><g:textField name="plaintiffCaption" value="${plaintiffCaption}" /></td>
	</tr>
	<tr>
		<td>image</td>
		<td><input type="file" name="image" /></td>
	</tr>
	<tr>
		<td>defendant</td>
		<td>
			<input id="defendantName" type="text" />
			<r:img dir="images" file="spinner.gif" id="defendantSpinner" style="display:none;" />
		</td>
	</tr>
	<tr>
		<td><input type="button" value="cancel" /></td>
		<td><input type="submit" value="create" /></td>
	</tr>
</table>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>What are you up to?</title>
	<g:javascript library="prototype"/>
<head>
<body>

	<h1>What are you up to?</h1>
	<div class= "updateStatusForm">
	  <g:formRemote onSuccess="document.UpdateStatusForm.message.value=''" url="[action: 'updateStatus']"
		update="messageList" name="updateStatusForm">
		<g:textArea name="message" value=""/><br/>
		<g:submitButton name="Update Status"/>
	  </g:formRemote>
	 </div> 
	<div id="messageList">
	 <g:render template="messages" collection="${messages}" var="message"/>
	</div>
</body>
</html>

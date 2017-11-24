
			var video = document.getElementById("myVideo");
			var source = document.getElementById("mySource");
			
			function myFunctionPause() {
				var data = new FormData();
					data.append('paused', video.currentTime);
					data.append('video', source.src);
				
				var http = new XMLHttpRequest();
				var url = "http://localhost:8080/spring-social-login/eventosVideo/pausa";
				
			    http.open("POST", url, true);
				http.withCredentials = true;
			    http.onload = function () {
			    // do something to response
			    	console.log(this.responseText);
				};
				http.send(data);
			    //alert("The video was paused."+ video.currentTime);
			}
			
			function myFunctionPlay() {
				var data = new FormData();
				data.append('played', video.currentTime);
				
			
				var http = new XMLHttpRequest();
				var url = "http://localhost:8080/spring-social-login/eventosVideo/play";
				
				http.open("POST", url, true);
				http.withCredentials = true;
				http.onload = function () {
				// do something to response
					console.log(this.responseText);
				};
				http.send(data);
			}
			
			(function(){
			    var pubnub = new PubNub({ publishKey : 'pub-c-ce660a2c-420f-47c6-a0ba-b3969f98f792', subscribeKey : 'sub-c-41658262-ce4e-11e7-b83f-86d028961179' });
			    function $(id) { return document.getElementById(id); }
			    var box = $('box'), input = $('input'), channel = '10chat-demo';
			    pubnub.addListener({
			        message: function(obj) {
			            if (obj.message.startsWith(source.src)){
			            	box.innerHTML = '<br>' + (''+obj.message.slice(source.src.length, this.length)) + '</br>' + box.innerHTML	
			            }
			            
			        }});
			    pubnub.subscribe({channels:[channel]});
			    input.addEventListener('keyup', function(e) {
			        if ((e.keyCode || e.charCode) === 13) {
			          pubnub.publish({channel : channel,message : source.src+input.value,x : (input.value='')});
			      }
			    });
			})();
		
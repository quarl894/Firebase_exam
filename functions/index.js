const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendMSGEvaluationArrival = functions.database.ref('/Request receipt/{}/{id}').onCreate((event) => {  
  const reqEmail = event.params.id;
  
  console.log("reqEmail : " + reqEmail);
  console.log("value : " + event.params.id.val());


	const getDeviceTokenPromise = admin.database().ref(`/Member Information/{}/${reqEmail}`).once('value');
  const getEvaluatorPromise = admin.auth().getUserByEmail(reqEmail);

	return Promise.all([getDeviceTokenPromise, getEvaluatorPromise]).then((results) => {
		const tokensSnapshot = results[0];
    const evaluator = results[1];
    
    console.log("tokenSnapshot = " + tokensSnapshot);
    console.log("evaluator = " + evaluator);
		
		if (!tokensSnapshot.hasChildren()) {
			return console.log('user에 토큰관련 document 없음');
		}

		console.log('토큰수 : ', tokensSnapshot.numChildren());
   	console.log('평가자 : ', evaluator);
		
		const payload = {
			notification : {
				title : '평가서가 도착했습니다!',
				// * user collection의 이름들이 name이 key가 맞는지 확인
				body : `${evaluator.name}님의 평가 보러가기`,
				// * 가야할 액티비티(나중에 구현)
				// click_action : ''
			}
		};

		// * 'token' : true 이런 형식으로 온다면
		const tokens = Object.keys(tokensSnapshot.val());
		
		return admin.messaging().sendToDevice(tokens, payload);

	}).then((response) => {
		
		const tokensToRemove = [];

		response.results.forEach((result, index) => {
			
			const error = result.error;
			if (error) {
				console.log('실패 토큰 : ', tokens[index], ', 에러 : ' ,error);
				
				// 토큰관련 에러처리
				if (error.code === 'messaging/invalid-registration-token' || error.code === 'messaging/registration-token-not-registered') {
					// * user collection 구조에 따라 바꿔야할 부분(token위치)
					tokensToRemove.push(tokensSnapshot.ref.child(tokens[index]).remove());
				}
			}

		});
		return Promise.all(tokensToRemove);

	});

});

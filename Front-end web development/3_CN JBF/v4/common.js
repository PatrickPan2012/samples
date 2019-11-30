function drawPieChart(canvasId, jsonArray, offsetX = 120, offsetY = 175, radius = 100){
	const canvas = document.getElementById(canvasId)
	
	let context = canvas.getContext("2d")
	context.clearRect(0,0,canvas.width,canvas.height)
	let lastsum = 0
	let sum = 0
	
	jsonArray.forEach(json => sum += json.num)

	for (let i = 0; i < jsonArray.length;i++) {
		lastSum(i)//上一个结束弧度就是下一个起始弧度
		let startAngle= (Math.PI*2)*(lastsum/sum)//起始弧度
		lastSum(i+1)
		let endAngle=(Math.PI*2)*(lastsum/sum)//结束弧度
		context.save()
		context.fillStyle=jsonArray[i].color
		context.beginPath()
		context.moveTo(offsetX,offsetY)
		context.arc(offsetX,offsetY,radius,startAngle,endAngle)
		context.closePath()
		context.fill()
		context.restore()
		drawText(startAngle,endAngle,jsonArray[i].name,jsonArray[i].num/sum)
	}
	
	alert(`总收入：${sum}`)
	
	//下一个起始
	function lastSum(i){
		lastsum = 0
		for (let j = 0; j < i; j++) {
			lastsum += jsonArray[j].num
		}
	}
	
	//绘制文本和线段
	function drawText(s,e,jn,jsm){
		//文字的x，y坐标计算
		let x = Math.cos((s+e)/2)*(radius+60)+offsetX
		let y = Math.sin((s+e)/2)*(radius+60)+offsetY
		context.fillStyle="blue"
		context.fillText(jn,x,y)
		context.fillStyle = "red"
		//百分比精确到小数后两位
		context.fillText((parseInt(jsm*10000)/100)+"%",x,y+20)
		//绘制由每个饼指向文字的线段 
		context.beginPath()
		//各端点坐标由每块的起始弧度和结束弧度求平均后计算得出
		context.moveTo(Math.cos((s+e)/2)*radius+offsetX,Math.sin((s+e)/2)*radius+offsetY)
		context.lineTo( Math.cos((s+e)/2)*(radius+40)+offsetX, Math.sin((s+e)/2)*(radius+40)+offsetY)
		context.closePath()
		context.fillStyle="red"
		context.stroke()
	}
}
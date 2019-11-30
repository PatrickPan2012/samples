function drawAxis(canvasId, canvasPadding, textOfY){
	const canvas = document.getElementById(canvasId)
	const context = canvas.getContext('2d')

	const width = canvas.width
	const height = canvas.height
				
	// 原点坐标
	const xAxisOfO = canvasPadding
	const yAxisOfO = height - canvasPadding

	context.beginPath()
	context.lineWidth = 1
				
	// x轴
	context.moveTo(xAxisOfO, yAxisOfO) 
	context.lineTo(width - canvasPadding, height - canvasPadding)
	context.stroke()

	// y轴
	context.moveTo(xAxisOfO, yAxisOfO)
	context.lineTo(xAxisOfO, canvasPadding)
	context.stroke()
	context.fillText(textOfY, xAxisOfO - 15, canvasPadding - 5)
					
	return new Map([['xAxisOfO', xAxisOfO],
					['yAxisOfO', yAxisOfO],
					['context', context],
					['width', width],
					['height', height]])
}

function drawColumnChart(canvasId, canvasPadding, textOfY, data, color = '#FE911A', heightRatioOfMaxIndex = 0.8, heightRatioOfRect = 0.8, xOffsetOfIndexText = 35) {
	const map = drawAxis(canvasId, canvasPadding, textOfY)
	const xAxisOfO = map.get('xAxisOfO')
	const yAxisOfO = map.get('yAxisOfO')
	const context = map.get('context')
	const width = map.get('width')
	const height = map.get('height')

	const heightOfMaxIndex = (height - 2 * canvasPadding) * heightRatioOfMaxIndex
	const sortedData = data.sort((pre,next) => pre - next)

	const maxNum = sortedData[sortedData.length - 1]

	sortedData.forEach(function(e){
		const indexheight = heightOfMaxIndex * e/maxNum

		// 刻度
		const xAxis = xAxisOfO
		const yAxis = yAxisOfO - indexheight
		context.beginPath()
		context.setLineDash([])
		context.moveTo(xAxis, yAxis)
		context.lineTo(xAxis + 5, yAxis)
		context.stroke()
		context.fillText(e, xAxis - xOffsetOfIndexText, yAxis + 3)
							
		// 虚线
		context.beginPath()
		context.setLineDash([1, 1]);
		context.moveTo(xAxis + 5, yAxis)
		context.lineTo(width - canvasPadding, yAxis)
		context.stroke()
	})
						
	// 柱状图
	const heightOfRect = heightOfMaxIndex * heightRatioOfRect
	const widthOfRect = (width - 2 * canvasPadding)/3
						
	const yAxisOfRect = yAxisOfO - heightOfRect
	const xAxisOfRect = xAxisOfO + widthOfRect
						
	context.fillStyle = color
	context.fillRect(xAxisOfRect, yAxisOfRect, widthOfRect, heightOfRect)
}

function drawPieChart(canvasId, jsonArray, offsetX = 120, offsetY = 175, radius = 100){
	let context = document.getElementById(canvasId).getContext("2d")
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
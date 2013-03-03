YMaps
		.jQuery(window)
		.load(
				function() {
					var map = new YMaps.Map(YMaps.jQuery("#YMapsID-3511")[0]);
					map.setCenter(new YMaps.GeoPoint(33.346147, 47.908412), 16,
							YMaps.MapType.MAP);
					map.addControl(new YMaps.Zoom());
					map.addControl(new YMaps.ToolBar());
					YMaps.MapType.PMAP.getName = function() {
						return "Народная";
					};
					map.addControl(new YMaps.TypeControl([ YMaps.MapType.MAP,
							YMaps.MapType.SATELLITE, YMaps.MapType.HYBRID,
							YMaps.MapType.PMAP ], [ 0, 1, 2, 3 ]));

					YMaps.Styles.add("constructor#FF3732c85Polyline", {
						lineStyle : {
							strokeColor : "FF3732c8",
							strokeWidth : 5
						}
					});

					// Создает стиль
					var marker_stop = new YMaps.Style();

					// Создает стиль значка метки
					marker_stop.iconStyle = new YMaps.IconStyle();

					marker_stop.iconStyle.href = "/resources/public/css/ico/yandexMap/marker_stop.png";
					marker_stop.iconStyle.size = new YMaps.Point(97, 28);
					marker_stop.iconStyle.offset = new YMaps.Point(-25, -1);

					// Создает стиль
					var marker_optika = new YMaps.Style();

					// Создает стиль значка метки
					marker_optika.iconStyle = new YMaps.IconStyle();

					marker_optika.iconStyle.href = "/resources/public/css/ico/yandexMap/marker_optika.png";
					marker_optika.iconStyle.size = new YMaps.Point(60, 28);
					marker_optika.iconStyle.offset = new YMaps.Point(-1, -27);

					// Создает метку
					var placemark = new YMaps.Placemark(new YMaps.GeoPoint(
							33.344561, 47.908186), {
						style : marker_stop
					});

					// Устанавливает содержимое балуна
					placemark.name = "Остановка";
					placemark.description = "Площадь Освобождения";

					// Добавляет метку на карту
					map.addOverlay(placemark);

					// Создает метку
					placemark = new YMaps.Placemark(new YMaps.GeoPoint(
							33.348509, 47.908777), {
						style : marker_optika
					});

					// Устанавливает содержимое балуна
					placemark.name = "Оптика Щасливий погляд";
					placemark.description = "г. Кривой Рог, ул. Пушкина 11, т. (0564)908622";

					// Добавляет метку на карту
					map.addOverlay(placemark);

					map.addOverlay(createObject("Polyline", [
							new YMaps.GeoPoint(33.34853, 47.908705),
							new YMaps.GeoPoint(33.348402, 47.908575),
							new YMaps.GeoPoint(33.34469, 47.90882),
							new YMaps.GeoPoint(33.344454, 47.908229) ],
							"constructor#FF3732c85Polyline", "Остановка"));

					function createObject(type, point, style, description) {
						var allowObjects = [ "Placemark", "Polyline", "Polygon" ], index = YMaps.jQuery
								.inArray(type, allowObjects), constructor = allowObjects[(index == -1) ? 0
								: index];
						description = description || "";

						var object = new YMaps[constructor](point, {
							style : style,
							hasBalloon : !!description
						});
						object.description = description;

						return object;
					}
				});
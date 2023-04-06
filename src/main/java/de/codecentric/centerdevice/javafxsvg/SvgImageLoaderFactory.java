package de.codecentric.centerdevice.javafxsvg;

import java.io.IOException;
import java.io.InputStream;

import com.sun.javafx.iio.ImageFormatDescription;
import com.sun.javafx.iio.ImageLoader;
import com.sun.javafx.iio.ImageLoaderFactory;
import com.sun.javafx.iio.ImageStorage;

import de.codecentric.centerdevice.javafxsvg.dimension.DefaultDimensionProvider;
import de.codecentric.centerdevice.javafxsvg.dimension.DimensionProvider;

public class SvgImageLoaderFactory implements ImageLoaderFactory {
	private static final SvgImageLoaderFactory instance = new SvgImageLoaderFactory();

	private static DimensionProvider dimensionProvider;

	public static final void install() {
		install(new DefaultDimensionProvider());
	}

	public static final void install(DimensionProvider dimensionProvider) {
		SvgImageLoaderFactory.dimensionProvider = dimensionProvider;

		ImageStorage.getInstance().addImageLoaderFactory(instance);
	}

	public static final ImageLoaderFactory getInstance() {
		return instance;
	}

	@Override
	public ImageFormatDescription getFormatDescription() {
		return SvgDescriptor.getInstance();
	}

	@Override
	public ImageLoader createImageLoader(InputStream input) throws IOException {
		return new SvgImageLoader(input, dimensionProvider);
	}
}

from setuptools import setup


setup(name='pytimeextractor',
      version='0.1.3',
      description='Time Extractor NLP project - locate dates and times in text documents',
      keywords='NLP text extraction time date',
      url='https://github.com/ilbey/dws-library-turkish',
      author='Digamma.ai',
      author_email='info@digamma.ai',
      license='MIT',
      install_requires=[
          'pyjnius==1.4.2',
      ],
      packages=['pytimeextractor'],
      include_package_data=True,
      test_suite='nose.collector',
      tests_require=['nose'],
      zip_safe=False)

package com.pitt.cc.MiniProject1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Ngrams {

	public static class NGramMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

		private int n;

		@Override
		public void setup(Context context) throws IOException, InterruptedException {
			n = context.getConfiguration().getInt("n", 1);
		}

		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString().toLowerCase().replaceAll("[^a-z]+", " ");
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();
				for (int i = 0; i <= word.length() - n; i++) {
					String ngram = word.substring(i, i + n);
					context.write(new Text(ngram), new IntWritable(1));
				}
			}
		}
	}

	public static class NGramReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable value : values) {
				sum += value.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("Usage: NGramFrequency <n> <input> <output>");
			System.exit(2);
		}
		Configuration conf = new Configuration();
		conf.setInt("n", Integer.parseInt(args[0]));
		Job job = Job.getInstance(conf, "NGramFrequency");
		job.setJarByClass(Ngrams.class);
		job.setMapperClass(NGramMapper.class);
		job.setCombinerClass(NGramReducer.class);
		job.setReducerClass(NGramReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

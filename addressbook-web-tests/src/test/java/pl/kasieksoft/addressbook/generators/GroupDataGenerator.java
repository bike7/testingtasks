package pl.kasieksoft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import pl.kasieksoft.addressbook.model.GroupData;
import pl.kasieksoft.addressbook.model.GroupDataBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    private int count;

    @Parameter(names = "-f", description = "Target file")
    private String file;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jcommander = new JCommander(generator);
        try {
            jcommander.parse(args);
        } catch (ParameterException ex) {
            jcommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private void save(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(GroupDataBuilder.aGroupData()
                    .withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i))
                    .build());
        }
        return groups;
    }

}

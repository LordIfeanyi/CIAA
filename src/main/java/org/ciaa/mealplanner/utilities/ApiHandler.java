package org.ciaa.mealplanner.utilities;

import com.spoonacular.MiscApi;
import com.spoonacular.RecipesApi;
import com.spoonacular.client.ApiClient;
import com.spoonacular.client.ApiException;
import com.spoonacular.client.Configuration;
import com.spoonacular.client.auth.ApiKeyAuth;
import com.spoonacular.client.model.SearchAllFood200Response;
import com.spoonacular.client.model.SearchAllFood200ResponseSearchResultsInner;
import com.spoonacular.client.model.SearchAllFood200ResponseSearchResultsInnerResultsInner;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.ciaa.mealplanner.CiaaApplication;
import org.ciaa.mealplanner.User;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

public class ApiHandler
{
    private static final ApiClient CLIENT;

    static {
        try {
            prepareClasses();
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }

        CLIENT = Configuration.getDefaultApiClient();
        ApiKeyAuth authentication = (ApiKeyAuth) CLIENT.getAuthentication("apiKeyScheme");
        authentication.setApiKey("4dc6eb4bd1ec453dbba335faac5055d8");
        initializeResponses();

        MiscApi api = new MiscApi(CLIENT);
        try {
            SearchAllFood200Response response = api.searchAllFood("kale", 10, 10);
            CiaaApplication.getApplicationLog().debug("Response: " + response.toString());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static void prepareClasses() throws UnmodifiableClassException {
        Instrumentation instrumentation = ByteBuddyAgent.install();

        ClassFileTransformer classNodeReader = new ClassFileTransformer()
        {
            private int passes = 0;

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                if (className.equals("org/ciaa/mealplanner/utilities/ApiHandler$RecipesApiMod") && passes == 0) {
                    CiaaApplication.getApplicationLog().debug("Reading RecipesApiMod");

                    ClassNode classNode = new ClassNode();
                    ClassReader reader = new ClassReader(classfileBuffer);
                    reader.accept(classNode, 0);

                    classNode.methods.removeIf(methodNode -> methodNode.name.equals("<clinit>") || methodNode.name.equals("<init>"));

                    passes++;
                }
                return classfileBuffer;
            }
        };

        instrumentation.addTransformer(classNodeReader, true);
        instrumentation.retransformClasses(RecipesApiMod.class);
        instrumentation.removeTransformer(classNodeReader);

        ClassFileTransformer recipesApiTransformer = new ClassFileTransformer()
        {
            private int passes = 0;

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer) {
                if (!className.equals("com/spoonacular/RecipesApi")) return classfileBuffer;
                if (passes == 1) CiaaApplication.getApplicationLog().debug("Transforming RecipesApi");

                ClassNode classNode = new ClassNode();
                ClassReader reader = new ClassReader(classfileBuffer);
                reader.accept(classNode, 0);

                InsnList instructions = classNode.methods.stream()
                      .filter(methodNode -> methodNode.name.equals("getRandomRecipesCall"))
                      .findFirst()
                      .orElseThrow()
                      .instructions;

                System.out.println(instructions.size());

                passes++;
                return classfileBuffer;
            }
        };

        instrumentation.addTransformer(recipesApiTransformer, true);
        instrumentation.retransformClasses(RecipesApi.class);
        instrumentation.removeTransformer(recipesApiTransformer);
    }

    private static void initializeResponses() {
        SearchAllFood200Response.openapiFields.add("sorting");
        SearchAllFood200Response.openapiFields.add("filterMapping");
        SearchAllFood200Response.openapiFields.add("filterOptions");
        SearchAllFood200Response.openapiFields.add("activeFilterOptions");
        SearchAllFood200Response.openapiFields.add("expires");
        SearchAllFood200Response.openapiFields.add("isStale");

        SearchAllFood200ResponseSearchResultsInner.openapiFields.add("type");
        SearchAllFood200ResponseSearchResultsInner.openapiFields.add("totalResultsVariants");

        SearchAllFood200ResponseSearchResultsInnerResultsInner.openapiFields.add("dataPoints");
    }

    public static void suggestMeals(User user) {
        RecipesApi api = new RecipesApi(CLIENT);

        //api.getRandomRecipes(false, )
    }

    private static class RecipesApiMod
    {
        private static String[] splitString(String string) {
            String[] test = string.split(":");
            return test;
        }
    }
}
